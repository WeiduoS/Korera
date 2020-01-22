package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.UserDao;
import com.itlize.Korera.entities.SysRole;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("UserServicesImpl")
public class UserServicesImpl implements UserServices {

    @Autowired
    @Qualifier("UserDaoImpl")
    private UserDao ud;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int addUser(User user) {
        if(user == null) return -1;
        User u = ud.getUserByName(user.getUser_name());
        int res = -1;
        if(u != null) return -2;
        else{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            res = ud.addUser(user);
        }
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
        User u = ud.getUserByName(user.getUser_name());
        int res = -1;
        if(u != null) return -2;
        else{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            res = ud.saveOrUpdateUser(user);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
    public User getUserByName(String user_name) {
        if(user_name == null) return null;
        User user = ud.getUserByName(user_name);
        return user;
    }

    @Override
    public int removeUser(User user) {
        if(user == null) return -1;
        int res = ud.removeUser(user);
        return res;
    }

    @Override
    public BigInteger getUserSize() {
        return ud.getUserSize();
    }

    @Override
    public List<User> paginationUser(Integer startIndex, Integer pageSize) {
        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) return new ArrayList<>();
        List<User> list = ud.paginationUser(startIndex, pageSize);
        return list;
    }

    @Override
    public Map<String, Object> toAddRolePage(Integer id) {
        return null;
    }

    @Override
    public void addRoleToUser(Integer user_id, Integer[] ids) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = ud.getUserByName(username);
            if(user == null) return null;

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(SysRole role : user.getRoles()) authorities.add(new SimpleGrantedAuthority(role.getROLE_NAME()));
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            UserDetails ures = new org.springframework.security.core.userdetails.User(
//                    user.getUser_name(),
//                    "{noop}" + user.getPassword(),
//                    authorities);

            UserDetails ures = new org.springframework.security.core.userdetails.User(
                    user.getUser_name(), user.getPassword(),
                    authorities);
            System.out.println("user details:" + ures.toString());
            return ures;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
