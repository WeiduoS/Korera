package com.itlize.Korera.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.dao.UserDao;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/22 - 12:09 PM
 */
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoImplTest {

    @Autowired
    @Qualifier("UserDaoImpl")
    UserDao ud;

    @Test
    public void addUser() {
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
    public void updateUser() {
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
    public void saveOrUpdateUser() {
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
    public void listUsers() {
        List<User> list = ud.listUsers();

        System.out.println(list);
    }

    @Test
    public void getUserById() throws JsonProcessingException {
//        User user = ud.getUserById(1);
//        System.out.println(user.toString());

        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(user);
//        System.out.println("user json: " + json);

        String fakeUser = "{\n" +
                "\t\"user_name\":\"xxx02\",\n" +
                "\t\"password\":\"123\",\n" +
                "\t\"remember_me\":\"remember-me\"\n" +
                "}";
//        fakeUser = mapper.writeValueAsString(fakeUser);
        User user = mapper.readValue(fakeUser, User.class);
        System.out.println("after user json: " + user.toString());
    }

    @Test
    public void getUserByName() {
        List<User> users = ud.getUserByName("Hello 2");
        System.out.println(users);
    }

    @Test
    public void removeUser() {
        int res = ud.removeUser(ud.getUserById(3));
        System.out.println("res: " + res);
    }

    @Test
    public void getUserSize() {
        BigInteger res = ud.getUserSize();
        System.out.println(res);
    }

    @Test
    public void paginationUser() {
        int pageIndex = 1, pageSize = 3;
        List<User> list = ud.paginationUser(pageIndex, pageSize);
        System.out.println(list);
    }
}