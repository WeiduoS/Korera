package com.itlize.Korera.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author Weiduo
 * @date 2020/1/24 - 12:08 PM
 */
public class JdbcDriverListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 解决Tomcat mysql 驱动内存泄漏，手动注销JDBC
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
            } catch (SQLException ex) {
            }
        }
//        AbandonedConnectionCleanupThread.shutdown(true);
    }
}
