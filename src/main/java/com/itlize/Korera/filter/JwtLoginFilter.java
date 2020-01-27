package com.itlize.Korera.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlize.Korera.commons.JwtUtils;
import com.itlize.Korera.commons.RsaManager;
import com.itlize.Korera.entities.JWTRemeberToken;
import com.itlize.Korera.entities.SysRole;
import com.itlize.Korera.entities.User;
import org.joda.time.DateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Weiduo
 * @date 2020/1/20 - 2:25 PM
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RsaManager rsaManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaManager rsaManager) {
        setAuthenticationManager(authenticationManager);
        this.rsaManager = rsaManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println("before:" + user.toString());
            if(user.getRemember_me() != null) {
                request.setAttribute("remember-me", user.getRemember_me());
            }
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    user.getUser_name(), user.getPassword());

            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Map resultMap = new HashMap();
                resultMap.put("code", HttpServletResponse.SC_UNAUTHORIZED);
                resultMap.put("msg", "username or password wrong");
                out.write(new ObjectMapper().writeValueAsString(resultMap));
                out.flush();
                out.close();
            }catch (Exception outEx){
                outEx.printStackTrace();
            }
            throw new RuntimeException(e);
        }

    }


    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        if(logger.isDebugEnabled()) {
//            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
//                    + authResult);
//        }
//
//        SecurityContextHolder.getContext().setAuthentication(authResult);

        getRememberMeServices().loginSuccess(request, response, authResult);

        User user = new User();
        user.setUser_name(authResult.getName());
        Set<SysRole> roles = new HashSet<>();
        for(Object role : authResult.getAuthorities().toArray()) {
            roles.add(new SysRole(role.toString()));
        }
        user.setRoles(roles);

        if(request.getAttribute("remember-me") != null) user.setRemember_me(String.valueOf(request.getAttribute("remember-me")));

        JWTRemeberToken token = JwtUtils.generateTokenExpireInMinutes(user, rsaManager.getPrivateKey(), 24 * 60);

        response.addHeader("Authorization", "Bearer " + token.getToken());
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code", HttpServletResponse.SC_OK);
            resultMap.put("msg", "authentication pass！");
            resultMap.put("user", user);
            resultMap.put("expire-time", DateTime.now().plusMinutes(24 * 60).toDateTime().toString("yyyy/MM/dd HH:mm:ss", Locale.US));
            resultMap.put("Authorization", "Bearer " + token.getToken());
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        }catch (Exception outEx){
            outEx.printStackTrace();
        }
    }

}
