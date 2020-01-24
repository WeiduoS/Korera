package com.itlize.Korera.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author Weiduo
 * @date 2020/1/23 - 9:38 PM
 */
@ComponentScan(
        basePackages = {"com.itlize.Korera.controller"},
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {
                                Controller.class,
                                RestController.class,
                                ControllerAdvice.class,
                                RestControllerAdvice.class
                        }
                )
        },
        useDefaultFilters = false
)
@Configuration
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/img/**")
                .addResourceLocations("/css/**")
                .addResourceLocations("/plugins/**");
    }
}
