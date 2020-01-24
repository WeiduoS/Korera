package com.itlize.Korera.config;

import com.itlize.Korera.listener.JdbcDriverListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author Weiduo
 * @date 2020/1/23 - 9:21 PM
 */
public class KoreraWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringConfig.class, SpringSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[0];
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(JdbcDriverListener.class);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);


        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        characterEncoding.setAsyncSupported(true);

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        FilterRegistration.Dynamic hiddenHttpMethod = servletContext.addFilter("hiddenHttpMethod", hiddenHttpMethodFilter);
        hiddenHttpMethod.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        hiddenHttpMethod.setAsyncSupported(true);

        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();

        FilterRegistration.Dynamic springSecurity = servletContext.addFilter("springSecurityFilterChain", springSecurityFilterChain);
        springSecurity.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        springSecurity.setAsyncSupported(true);

    }

}
