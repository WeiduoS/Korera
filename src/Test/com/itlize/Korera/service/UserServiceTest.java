package com.itlize.Korera.service;

import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/8 - 11:16 AM
 */

@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest {

    @Autowired
    @Qualifier("UserServicesImpl")
    UserServices us;

    @Test
    public void addUserTest() {
        User user = new User();
        user.setUser_name("test 01");
        user.setPassword("test 01");
        int res = us.addUser(user);
        System.out.println(res);
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setUser_id(1);
        user.setUser_name("test 01");
        user.setPassword("test 01");
        int res = us.updateUser(user);
        System.out.println(res);
    }

    @Test
    public void saveOrUpdateUserTest() {
        User user = new User();
        user.setUser_id(1);
        user.setUser_name("test 01");
        user.setPassword("test 01");
        int res = us.saveOrUpdateUser(user);
        System.out.println(res);
    }


    @Test
    public void listUsersTest() {
        List<User> list = us.listUsers();
        System.out.println(list.toString());
    }

    @Test
    public void getUserByIdTest() {
        User user = us.getUserById(1);
        System.out.println(user.toString());
    }


    @Test
    public void getUserByName() {
        User user = us.getUserByName("test 01");
        System.out.println(user);
    }

    @Test
    public void removeUserTest() {
        int res = us.removeUser(new User(3));
        System.out.println("res: " + res);
    }

    @Test
    public void paginationProjectTest() {
        int pageIndex = 1, pageSize = 1;
        List<User> list = us.paginationUser(pageIndex, pageSize);
        System.out.println(list);
    }

    @Test
    public void encodeTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("encode password: " + passwordEncoder.encode("hello"));
    }


}
