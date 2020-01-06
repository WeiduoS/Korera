package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.UserDao;
import com.itlize.Korera.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addUser(User user) {
        System.out.println(sessionFactory);
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public int updateUser(User user) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public int saveOrUpdateUser(User user) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public List<User> listUsers() {
        if(sessionFactory == null) return new ArrayList<User>();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String sql = "select * from User";
        Query query = session.createSQLQuery(sql);
        List<User> list = ((NativeQuery) query).addEntity(User.class).list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public User getUserById(Integer id) {
        if (sessionFactory == null) return null;
        Session session = sessionFactory.getCurrentSession();
        User user = null;
        try {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return user;
    }

    @Override
    public int removeUser(User user) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }
}
