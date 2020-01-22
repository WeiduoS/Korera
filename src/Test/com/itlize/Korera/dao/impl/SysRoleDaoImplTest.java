package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.SysRoleDao;
import com.itlize.Korera.dao.UserDao;
import com.itlize.Korera.entities.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/22 - 12:06 PM
 */
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SysRoleDaoImplTest {

    @Autowired
    @Qualifier("SysRoleDaoImpl")
    SysRoleDao sysRoleDao;

    @Test
    public void addSysRole() {

    }

    @Test
    public void getSysRoleById() {
    }

    @Test
    public void listSysRoles() {
        List<SysRole> list = sysRoleDao.listSysRoles();
        System.out.println(list);
    }

    @Test
    public void removeSysRole() {
    }
}