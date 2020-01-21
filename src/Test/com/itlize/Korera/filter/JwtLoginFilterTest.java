package com.itlize.Korera.filter;

import com.itlize.Korera.commons.RsaManager;
import com.itlize.Korera.service.impl.UserServicesImpl;
import org.junit.Test;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/21 - 10:25 AM
 */
public class JwtLoginFilterTest {

    @Test
    public void helper() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(new UserServicesImpl());
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        ProviderManager providerManager = new ProviderManager(Arrays.asList(daoAuthenticationProvider));

        RsaManager rsaManager = new RsaManager();

        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(providerManager, rsaManager);


        jwtLoginFilter.afterPropertiesSet();
    }

}