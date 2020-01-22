package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.SysRoleDao;
import com.itlize.Korera.entities.SysRole;
import com.itlize.Korera.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/22 - 12:02 PM
 */

@Repository(value = "SysRoleDaoImpl")
public class SysRoleDaoImpl implements SysRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addSysRole(SysRole role) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public SysRole getSysRoleById(Integer id) {
        if (sessionFactory == null) return null;
        Session session = sessionFactory.getCurrentSession();
        SysRole role = null;
        try {
            session.beginTransaction();
            role = session.get(SysRole.class, id);
            session.getTransaction().commit();
            return role;
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<SysRole> getSysRoleByName(String name) {
        if(name == null) return null;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            String sql = "select * from sys_role where ROLE_NAME=?";
            Query query = session.createSQLQuery(sql).setParameter(1, name);
            List<SysRole> list = ((NativeQuery) query).addEntity(SysRole.class).list();
            session.getTransaction().commit();
            return list;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<SysRole> listSysRoles() {
        if(sessionFactory == null) return new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String sql = "select * from sys_role";
        Query query = session.createSQLQuery(sql).addEntity(SysRole.class);
        List<SysRole> list = ((NativeQuery) query).list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public int removeSysRole(SysRole role) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.remove(role);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }
}
