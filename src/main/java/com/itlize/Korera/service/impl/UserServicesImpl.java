package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ProjectDao;
import com.itlize.Korera.dao.UserDao;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class UserServicesImpl implements UserServices {

    @Autowired
    @Qualifier("UserDaoImpl")
    UserDao ud;

    @Override
    public int addUser(User user) {
        if(user == null) return -1;
        int res = ud.addUser(user);
        return res;
    }

    @Override
    public int updateUser(User user) {
        if(user == null) return -1;
        int res = ud.updateUser(user);
        return res;
    }

    @Override
    public int saveOrUpdateUser(User user) {
        if(user == null) return -1;
        int res = ud.saveOrUpdateUser(user);
        return res;
    }

    @Override
    public List<User> listUsers() {
        List<User> res = ud.listUsers();
        return res == null ? new ArrayList<>() : res;
    }

    @Override
    public User getUserById(Integer id) {
        if(id == null || id < 0) return null;
        User res = ud.getUserById(id);
        return res;
    }

    @Override
    public int removeUser(User user) {
        if(user == null) return -1;
        int res = ud.removeUser(user);
        return res;
    }
}
