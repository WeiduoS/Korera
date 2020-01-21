package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoTest {
    @Autowired
    @Qualifier("UserDaoImpl")
    UserDao ud;

    @Test
    public void addUserTest() {
        User user = new User();
        for(int i = 1 ; i <= 3; i++) {
            user.setUser_name("User " + i);
            user.setPassword("password" + i);

            Set<Project> set = new HashSet<>();
            for(int j = 0; j < 5; j++) {
                set.add(new Project("p" + i, user));
            }

            user.setProjects(set);
            int res = ud.addUser(user);
            System.out.println("res: " + res);
        }
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setUser_id(1);
        user.setUser_name("User " + 2);
        user.setPassword("password" + 2);

        for(int i = 1 ; i <= 1; i++) {
            Set<Project> set = new HashSet<>();
            for(int j = 1; j <= 5; j++) {
                set.add(new Project(j,"p" + j * 11, user));
            }
            user.setProjects(set);
        }
        int res = ud.updateUser(user);
        System.out.println("res: " + res);
    }

    @Test
    public void saveOrUpdateUserTest() {
        User user = new User();
        user.setUser_id(1);
        user.setUser_name("User " + 2);
        user.setPassword("password" + 2);

        for(int i = 1 ; i <= 1; i++) {
            Set<Project> set = new HashSet<>();
            for(int j = 1; j <= 5; j++) {
                set.add(new Project(j,"p" + j * 10 * 10, user));
            }
            user.setProjects(set);
        }

        for(int i = 1 ; i <= 1; i++) {
            Set<Project> set = new HashSet<>();
            for(int j = 1; j <= 5; j++) {
                set.add(new Project( "p" + j * 10 * 10, user));
            }
            user.setProjects(set);
        }

        System.out.println(user.toString());
        int res = ud.saveOrUpdateUser(user);
        System.out.println("res: " + res);
    }

    @Test
    public void listUsersTest() {
        List<User> list = ud.listUsers();

        System.out.println(list);
    }

    @Test
    public void getUsersByIdTest() {
        User user = ud.getUserById(3);
        System.out.println(user.toString());
    }

    @Test
    public void getUsersByNameTest() {
        User user = ud.getUserByName("Hello 2");
        System.out.println(user);
    }

    @Test
    public void removeUsersTest() {
        int res = ud.removeUser(ud.getUserById(3));
        System.out.println("res: " + res);
    }

    @Test
    public void getUserSizeTest(){
        BigInteger res = ud.getUserSize();
        System.out.println(res);
    }

    @Test
    public void paginationUserTest() {
        int pageIndex = 1, pageSize = 3;
        List<User> list = ud.paginationUser(pageIndex, pageSize);
        System.out.println(list);
    }

}
