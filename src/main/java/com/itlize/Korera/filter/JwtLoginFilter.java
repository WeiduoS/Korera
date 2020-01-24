package com.itlize.Korera.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlize.Korera.commons.JwtUtils;
import com.itlize.Korera.commons.RsaManager;
import com.itlize.Korera.entities.SysRole;
import com.itlize.Korera.entities.User;
import org.joda.time.DateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
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
        User user = new User();
        user.setUser_name(authResult.getName());
        System.out.println("setting name:" + user.toString());
        Set<SysRole> roles = new HashSet<>();
        for(Object role : authResult.getAuthorities().toArray()) {
            roles.add(new SysRole(role.toString()));
        }
        user.setRoles(roles);
        System.out.println("roles: " + authResult.getAuthorities().toString());
        System.out.println("setting roles: " + user.toString());

        String token = JwtUtils.generateTokenExpireInMinutes(user, rsaManager.getPrivateKey(), 24 * 60);

        System.out.println("token: " + token);
        response.addHeader("Authorization", "Bearer " + token);
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code", HttpServletResponse.SC_OK);
            resultMap.put("msg", "authentication pass！");
            resultMap.put("user", user);
            resultMap.put("expired", DateTime.now().plusMinutes(24 * 60).toDateTime().toString("yyyy/MM/dd HH:mm:ss", Locale.US));
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        }catch (Exception outEx){
            outEx.printStackTrace();
        }
    }

}
