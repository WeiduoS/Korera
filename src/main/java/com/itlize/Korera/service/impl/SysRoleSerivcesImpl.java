package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.SysRoleDao;
import com.itlize.Korera.entities.SysRole;
import com.itlize.Korera.service.SysRoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/22 - 12:18 PM
 */

@Service("SysRoleSerivcesImpl")
public class SysRoleSerivcesImpl implements SysRoleServices {
    @Autowired
    @Qualifier("SysRoleDaoImpl")
    SysRoleDao sysRoleDao;

    @Override
    public int addSysRole(SysRole role) {
        if(role == null || role.getROLE_NAME() == null || role.getROLE_NAME().equals("")) return -1;
        List<SysRole> list = sysRoleDao.getSysRoleByName(role.getROLE_NAME());
        if(!list.isEmpty()) return -2;
        int res = sysRoleDao.addSysRole(role);
        return res;
    }

    @Override
    public SysRole getSysRoleById(Integer id) {
        if(id == null || id < 0) return null;
        SysRole role = sysRoleDao.getSysRoleById(id);
        return role;
    }

    @Override
    public List<SysRole> getSysRoleByName(String name) {
        if(name == null || name.equals("")) return new ArrayList<>();
        List<SysRole> list = sysRoleDao.getSysRoleByName(name);
        return list;
    }

    @Override
    public List<SysRole> listSysRoles() {
        return sysRoleDao.listSysRoles();
    }

    @Override
    public int removeSysRole(SysRole role) {
        if(role == null || role.getSysRole_id() == null) return -1;
        int res = sysRoleDao.removeSysRole(role);
        return res;
    }
}
