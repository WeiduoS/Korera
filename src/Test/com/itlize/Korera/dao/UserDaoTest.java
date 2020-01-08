package com.itlize.Korera.dao;

import com.itlize.Korera.entities.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    UserDao ud = (UserDao) ac.getBean("UserDaoImpl");

    @Test
    public void addUserTest() {
        User user = new User();
        for(int i = 1 ; i <= 100; i++) {
            user.setUser_Id(i);
            user.setUser_name("User " + i);
            user.setPassword("password"+i);
            user.setIcon("User Icon "+ i);
            user.setJoin_date(new Date(12313123));
            user.setProject_id(1);
            user.setUser_name("Username"+i);
            int res = ud.addUser(user);
            System.out.println("res: " + res);
        }
    }

    @Test
    public void updateUserTest() {
        User user = new User();

        user.setUser_Id(1);
        user.setUser_name("Hello " + 1);
        user.setPassword("password"+1);
        user.setIcon("User Icon "+ 1);
        user.setJoin_date(new Date(12313123));
        user.setProject_id(1);
        user.setUser_name("Username"+1);

        int res = ud.updateUser(user);
        System.out.println("res: " + res);
    }

    @Test
    public void saveOrUpdateUserTest() {
        User user = new User();

        user.setUser_Id(101);
        user.setUser_name("Hello " + 1);
        user.setPassword("password"+1);
        user.setIcon("User Icon "+ 1);
        user.setJoin_date(new Date(12313123));
        user.setProject_id(1);
        user.setUser_name("Username"+1);

        int res = ud.saveOrUpdateUser(user);
        System.out.println("res: " + res);
    }

    @Test
    public void listUsersTest() {
        List<User> list = ud.listUsers();
        System.out.println(list.toString());
    }

    @Test
    public void getUsersByIdTest() {
        User user = ud.getUserById(1);
        System.out.println(user.toString());
    }

    @Test
    public void removeUsersTest() {

        Integer id = 1;

        int res = ud.removeUser(id);
        System.out.println("res: " + res);
    }

}
