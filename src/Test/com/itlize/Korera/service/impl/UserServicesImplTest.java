package com.itlize.Korera.service.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/29 - 2:25 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@Transactional
public class UserServicesImplTest {

    @Autowired
    @Qualifier(value = "UserServicesImpl")
    UserServices userServices;
    @Test
    public void addUser() {
        User user = new User();
        user.setUser_name("test 01");
        user.setPassword("test 01");
        int res = userServices.addUser(user);
        System.out.println(res);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUser_id(1);
        user.setUser_name("test 01");
        user.setPassword("test 01");
        int res = userServices.updateUser(user);
        System.out.println(res);
    }

    @Test
    public void saveOrUpdateUser() {
        User user = new User();
        user.setUser_id(1);
        user.setUser_name("test 01");
        user.setPassword("test 01");
        int res = userServices.saveOrUpdateUser(user);
        System.out.println(res);
    }

    @Test
    public void listUsers() {
        List<User> list = userServices.listUsers();
        System.out.println(list.toString());
    }

    @Test
    public void getUserById() {
        User user = userServices.getUserById(1);
        System.out.println(user.toString());
    }

    @Test
    public void getUserByName() {
        List<User> users = userServices.getUserByName("test 01");
        System.out.println(users);
    }

    @Test
    public void removeUser() {
        int res = userServices.removeUser(userServices.getUserById(7));
        System.out.println("res: " + res);
    }

    @Test
    public void getUserSize() {
    }

    @Test
    public void paginationUser() {
        int pageIndex = 1, pageSize = 1;
        List<User> list = userServices.paginationUser(pageIndex, pageSize);
        System.out.println(list);
    }

    @Test
    public void toAddRolePage() {
    }

    @Test
    public void addRoleToUser() {
    }

    @Test
    public void loadUserByUsername() {
    }
}