package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.ColsDao;
import com.itlize.Korera.entities.Cols;
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
 * @date 2020/1/10 - 8:26 AM
 */
@Repository(value = "ColsDaoImpl")
public class ColsDaoImpl implements ColsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addCols(Cols cols) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();
            session.save(cols);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }

    }

    @Override
    public int updateCols(Cols cols) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();
            session.update(cols);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }

    }

    @Override
    public List<Cols> getColsById(Integer project_id, Integer resource_id) {
        if(sessionFactory == null) return  null;
        Session session = sessionFactory.getCurrentSession();
        List<Cols> cols;

        try{
            session.beginTransaction();
            String sql = "select * from Cols where project_id=? and resource_id=?";
            Query query = session.createSQLQuery(sql);
            cols = (List<Cols>) ((NativeQuery) query).addEntity(Cols.class).
                    setParameter(1, project_id).setParameter(2, resource_id).list();
            session.getTransaction().commit();
            return cols;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Cols> listCols() {
        if(sessionFactory == null) return  null;
        Session session = sessionFactory.getCurrentSession();
        List<Cols> cols;
        try{
            session.beginTransaction();
            String sql = "select * from Cols";
            Query query = session.createSQLQuery(sql);
            cols = (List<Cols>) ((NativeQuery) query).addEntity(Cols.class).list();
            session.getTransaction().commit();
            return cols;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }

    }

    @Override
    public int removeCols(Cols cols) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.remove(cols);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }
}
