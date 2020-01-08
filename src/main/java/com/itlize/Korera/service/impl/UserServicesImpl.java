package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.UserDao;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserServicesImpl")
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
    public List<User> getUserByName(String user_name) {
        if(user_name == null) return new ArrayList<>();
        List<User> list = ud.getUserByName(user_name);
        return list;
    }

    @Override
    public int removeUser(Integer user_id) {
        if(user_id == null) return -1;
        int res = ud.removeUser(user_id);
        return res;
    }

    @Override
    public List<User> paginationUser(Integer startIndex, Integer pageSize) {
        if(startIndex <= 0 || pageSize <= 0) return new ArrayList<>();
        List<User> list = ud.paginationUser(startIndex, pageSize);
        return list;
    }

}
