package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.UserDao;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.SysRole;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.SysRoleServices;
import com.itlize.Korera.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service("UserServicesImpl")
public class UserServicesImpl implements UserServices {

    @Autowired
    @Qualifier("UserDaoImpl")
    private UserDao ud;

    @Autowired
    @Qualifier("SysRoleSerivcesImpl")
    SysRoleServices sysRoleServices;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int addUser(User user) {
        int res = 0;

        if(sysRoleServices == null || ud == null || user == null) res = -1;
        else{
            List<User> users = ud.getUserByName(user.getUser_name());
            if(users != null && users.size() >= 2) res = -2;
            else{
                List<SysRole> roles = sysRoleServices.getSysRoleByName("ROLE_USER");
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.getRoles().addAll(roles);
                res = ud.addUser(user);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("adding a User with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for duplicated. ]");
        }

        return res;
    }

    @Override
    public int updateUser(User user) {
        int res = 0;

        if(user == null || user.getUser_id() == null) res = -1;
        else {
            User userById = ud.getUserById(user.getUser_id());
            if(userById == null) res = -2;
            else{
                List<SysRole> roles = sysRoleServices.getSysRoleByName("ROLE_USER");
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.getRoles().addAll(roles);
                res = ud.updateUser(user);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("updating a User with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for no such user(id). ]");
        }

        return res;
    }

    @Override
    public int saveOrUpdateUser(User user) {
        int res = 0;

        if(user == null || user.getUser_name() == null) res = -1;
        else{
            List<User> list = ud.getUserByName(user.getUser_name());
            if(!list.isEmpty() && list.size() >= 2) res = -2;
            else if(list.size() == 1) {
                User dbUser = list.get(0);
                if(user.getUser_id() != null && dbUser.getUser_id() != user.getUser_id()) res = -2;
            }else{
                List<SysRole> roles = sysRoleServices.getSysRoleByName("ROLE_USER");
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.getRoles().addAll(roles);
                res = ud.saveOrUpdateUser(user);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("saveOrUpdate a Category with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for duplicated(user name). ]");
        }

        return res;
    }

    @Override
    public List<User> listUsers() {
        List<User> list = ud.listUsers();

        int res = list.size();

        if(logger.isDebugEnabled()) {
            logger.debug("list all User with size " + res);
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public User getUserById(Integer id) {
        User user;

        if(id == null) return null;
        else{
            user = ud.getUserById(id);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find User by id with result: " + (user == null ? " null" : user));
        }

        return user;
    }

    @Override
    public List<User> getUserByName(String user_name) {
        List<User> list;
        if(user_name == null) list = null;
        else{
            list = ud.getUserByName(user_name);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find User by name with size " + (list == null ? 0 : list.size()));
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public int removeUser(User user) {
        int res = 0;

        if(user == null || user.getUser_id() == null) res = -1;
        else{
            User removeUser = ud.getUserById(user.getUser_id());
            for(Project project : removeUser.getProjects()) {
                project.setResouces(new HashSet<>());
            }
            res = ud.removeUser(removeUser);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("delete a User with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(user or id) ]");
        }

        return res;
    }

    @Override
    public BigInteger getUserSize() {
        BigInteger res = ud.getUserSize();

        if(logger.isDebugEnabled()) {
            logger.debug("get User size with result " + res);
        }

        return res;
    }

    @Override
    public List<User> paginationUser(Integer startIndex, Integer pageSize) {
        List<User> list;

        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) list = null;
        else{
            list = ud.paginationUser(startIndex, pageSize);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find User by pagination with size " + (list == null ? 0 : list.size()));
        }

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
            List<User> users = ud.getUserByName(username);
            if(users == null || users.size() >= 2) throw new UsernameNotFoundException("duplicate username found");

            User user = users.get(0);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(SysRole role : user.getRoles()) authorities.add(new SimpleGrantedAuthority(role.getROLE_NAME()));

            UserDetails ures = new org.springframework.security.core.userdetails.User(
                    user.getUser_name(), user.getPassword(),
                    authorities);
            return ures;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
