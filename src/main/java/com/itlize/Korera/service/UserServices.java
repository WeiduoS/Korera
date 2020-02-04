package com.itlize.Korera.service;

import com.itlize.Korera.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface UserServices extends UserDetailsService {

    public int addUser(User user);

    public int updateUser(User user);

    public int saveOrUpdateUser(User user);

    public List<User> listUsers();

    public User getUserById(Integer id);

    public List<User> getUserByName(String user_name);

    public int removeUser(User user);

    public BigInteger getUserSize();

    public List<User> paginationUser(Integer startIndex, Integer pageSize);

    public Map<String, Object> toAddRolePage(Integer id);

    public void addRoleToUser(Integer user_id, Integer[] ids);


}