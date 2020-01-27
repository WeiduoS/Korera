package com.itlize.Korera.service.impl;

import com.itlize.Korera.commons.JwtUtils;
import com.itlize.Korera.commons.RsaManager;
import com.itlize.Korera.entities.JWTRemeberToken;
import com.itlize.Korera.entities.SysRole;
import com.itlize.Korera.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2020/1/26 - 3:32 PM
 */
public class JWTRememberSerivcesImpl extends AbstractRememberMeServices {

    private RsaManager rsaManager;

    private PersistentTokenRepository repository;

    public JWTRememberSerivcesImpl(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    public RsaManager getRsaManager() {
        return rsaManager;
    }

    public void setRsaManager(RsaManager rsaManager) {
        this.rsaManager = rsaManager;
    }

    public PersistentTokenRepository getRepository() {
        return repository;
    }

    public void setRepository(PersistentTokenRepository repository) {
        this.repository = repository;
    }

    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        if(request.getAttribute("remember-me") != null) {
            return request.getAttribute("remember-me").equals(parameter);
        }else {
            if (logger.isDebugEnabled()) {
                logger.debug("Did not send remember-me (principal did not set parameter '"
                        + parameter + "')");
            }
            return false;
        }
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {

        String username = authResult.getName();

        logger.debug("Creating new persistent login for user " + username);

        User user = new User();
        user.setUser_name(authResult.getName());
        Set<SysRole> roles = new HashSet<>();
        for(Object role : authResult.getAuthorities().toArray()) {
            roles.add(new SysRole(role.toString()));
        }
        user.setRoles(roles);

        JWTRemeberToken token = JwtUtils.generateTokenExpireInMinutes(user, rsaManager.getPrivateKey(), 24 * 60);

        try {
            repository.createNewToken(token);
        }
        catch (Exception e) {
            logger.error("Failed to save persistent token ", e);
        }
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException {
        return null;
    }
}
