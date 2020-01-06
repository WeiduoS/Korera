package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.User;

import java.util.List;

public interface UserDao {

    public int addUser(User user);

    public int updateUser(User user);

    public int saveOrUpdateUser(User user);

    public List<User> listUsers();

    public User getUserById(Integer id);

    public int removeUser(User user);
}
