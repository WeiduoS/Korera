package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.SysRole;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:48 PM
 */
public interface SysRoleDao {

    public int addSysRole(SysRole role);

    public SysRole getSysRoleById(Integer id);

    public List<SysRole> getSysRoleByName(String name);

    public List<SysRole> listSysRoles();

    public int removeSysRole(SysRole role);

}
