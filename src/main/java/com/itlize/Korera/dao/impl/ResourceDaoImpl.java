package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.ResourceDao;
import com.itlize.Korera.entities.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("ResourceDaoImpl")
public class ResourceDaoImpl implements ResourceDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addResource(Resource resource) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(resource);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public int updateResource(Resource resource) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            int id = resource.getResourceId();
            Resource rn = (Resource)session.get(Resource.class,id);
            rn.setResourceCode(resource.getResourceCode());
            rn.setResourceName(resource.getResourceName());
            session.update(rn);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public int saveOrUpdateResource(Resource resource) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(resource);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public List<Resource> listResources() {
        if(sessionFactory == null) return new ArrayList<Resource>();
        Session session = sessionFactory.getCurrentSession();
        List<Resource> list = null;
        try {
            session.beginTransaction();
            String sql = "from Resource";
            Query query = session.createQuery(sql);
            list = query.list();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Resource getResourceById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Resource resource = null;
        try{
            session.beginTransaction();
            resource = session.get(Resource.class,id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return resource;


    }

    @Override
    public List<Resource> getResourceByName(String resource_name) {
        if(sessionFactory == null) return new ArrayList<Resource>();
        Session session = sessionFactory.getCurrentSession();
        List<Resource> list = null;
        try {
            session.beginTransaction();
            String sql = "from Resource r where r.resourceName = :name";
            Query query = session.createQuery(sql).setParameter("name",resource_name);
            list = query.list();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public int removeResource(Resource resource) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.delete(resource);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public List<Resource> paginationResource(Integer startIndex, Integer pageSize) {
        if(sessionFactory == null) return new ArrayList<Resource>();
        Session session = sessionFactory.getCurrentSession();
        List<Resource> list = null;
        try{
            session.beginTransaction();
            int limit = (startIndex - 1) * pageSize;
            Query query = session.createQuery("from Resource ").setFirstResult(limit).setMaxResults(pageSize);
            list = query.list();
            session.getTransaction().commit();
        }catch (Exception e){
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
        return list;
    }
}
