package com.itlize.Korera.commons;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Weiduo
 * @date 2020/1/23 - 11:28 AM
 */
@Component(value = "DatabaseInitializer")
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    @Qualifier(value = "dataSource2")
    DataSource dataSource;

    DatabaseInitializer() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createDatabasePopulator();
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("DBInit/build.sql"));
        databasePopulator.addScript(new ClassPathResource("DBInit/initial.sql"));
        databasePopulator.execute(dataSource);
        return databasePopulator;
    }


}
