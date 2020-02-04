package com.itlize.Korera.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlize.Korera.commons.JwtUtils;
import com.itlize.Korera.commons.RsaManager;
import com.itlize.Korera.entities.Payload;
import com.itlize.Korera.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Weiduo
 * @date 2020/1/20 - 2:44 PM
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private RsaManager rsaManager;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaManager rsaManager) {
        super(authenticationManager);
        this.rsaManager = rsaManager;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        String path = request.getServletPath();

        if(path.equals("/user/add") || path.equals("/user/sign-up")){
            chain.doFilter(request, response);
        }else if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
            resultMap.put("msg", "please login");
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        } else {

            try{
                String token = header.replace("Bearer ", "");
                Payload<User> payload = JwtUtils.getInfoFromToken(token, rsaManager.getPublicKey(), User.class);
                User user = payload.getUserInfo();

                if(user != null){
                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getUser_name(), "", user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authResult);
                    request.setAttribute("user_id", user.getUser_id());

                    chain.doFilter(request, response);
                }else{

                    throw new BadCredentialsException("authenticate failed!!");
                }

            }catch (IOException e){
                e.printStackTrace();

            }catch (ServletException e) {

                e.printStackTrace();

            }catch (AuthenticationException e){

                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter out = response.getWriter();
                Map resultMap = new HashMap();
                resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
                resultMap.put("msg", "please login");
                out.write(new ObjectMapper().writeValueAsString(resultMap));
                out.flush();
                out.close();
            }
        }

    }


}
