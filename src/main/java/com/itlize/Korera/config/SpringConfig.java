package com.itlize.Korera.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * @author Weiduo
 * @date 2020/1/23 - 9:28 PM
 */

@ComponentScan(
        basePackages = "com.itlize.Korera",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                        Controller.class,
                        RestController.class,
                        ControllerAdvice.class,
                        RestControllerAdvice.class

                }),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                        SpringMVCConfig.class
                })
        }
)
@Configuration
@PropertySource(value = {"classpath:/config/hibernate.properties","classpath:/config/rsa.properties"})
@EnableTransactionManagement
public class SpringConfig {

        @Autowired
        Environment env;

        @Bean(value = "dataSource", destroyMethod = "close")
        public ComboPooledDataSource dataSource() {
                ComboPooledDataSource dataSource = new ComboPooledDataSource();
                try {
                        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
                        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
                        dataSource.setUser(env.getProperty("jdbc.username"));
                        dataSource.setPassword(env.getProperty("jdbc.password"));
                        dataSource.setInitialPoolSize(10);
                        dataSource.setMaxPoolSize(20);
                        dataSource.setMinPoolSize(5);
                        dataSource.setMaxIdleTime(120);
                        dataSource.setAcquireIncrement(2);
                        dataSource.setIdleConnectionTestPeriod(60);
                        dataSource.setPrivilegeSpawnedThreads(true);
                        dataSource.setContextClassLoaderSource("library");
                } catch (PropertyVetoException e) {
                        e.printStackTrace();
                }

                return dataSource;
        }

        @Bean(value = "sessionFactory")
        public LocalSessionFactoryBean sessionFactoryBean() {
                LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
                sessionFactory.setDataSource(dataSource());
                sessionFactory.setPackagesToScan("com.itlize.Korera.entities");
                Properties properties = new Properties();
                properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
                properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
                properties.setProperty("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));
                sessionFactory.setHibernateProperties(properties);
                return sessionFactory;
        }

        @Bean(value = "transactionManager")
        public PlatformTransactionManager hibernateTransactionManager() {
                HibernateTransactionManager transactionManager = new HibernateTransactionManager();
                transactionManager.setSessionFactory(sessionFactoryBean().getObject());
                return transactionManager;
        }

}
