package com.itlize.Korera.config;

import com.itlize.Korera.commons.RsaManager;
import com.itlize.Korera.filter.JwtLoginFilter;
import com.itlize.Korera.filter.JwtVerifyFilter;
import com.itlize.Korera.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author Weiduo
 * @date 2020/1/24 - 10:38 AM
 */

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public SpringSecurityConfig() {
    }

        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServices).passwordEncoder(passwordEncoder());
    }

    @Autowired
    @Qualifier(value = "UserServicesImpl")
    UserServices userServices;

    @Autowired
    @Qualifier(value = "RsaManager")
    RsaManager rsaManager;

    @Autowired
    @Qualifier(value = "dataSource")
    DataSource dataSource;

    @Bean(value = "passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.applyPermitDefaultValues();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean("JwtLoginFilter")
    public JwtLoginFilter jwtLoginFilter() {
        try {
            JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(super.authenticationManager(), rsaManager);
            return jwtLoginFilter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Bean("JwtVerifyFilter")
    public JwtVerifyFilter jwtVerifyFilter() {
        try {
            JwtVerifyFilter jwtVerifyFilter = new JwtVerifyFilter(super.authenticationManager(), rsaManager);
            return jwtVerifyFilter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/resources/**", "/plugins/**", "/css/**", "/js/**")
                .permitAll()
                .antMatchers("/login", "/user/sign-up", "/user/add")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ROLE_USER")
                .anyRequest()
                .authenticated()
            .and().formLogin()
                .loginProcessingUrl("/login")
                .failureForwardUrl("/login")
            .and().cors()
                .configurationSource(corsConfigurationSource())
            .and().rememberMe()
                .tokenValiditySeconds(60)
                .rememberMeParameter("remember-me")
                .tokenRepository(tokenRepository())
            .and().addFilter(jwtLoginFilter())
                .addFilter(jwtVerifyFilter());

    }

}
