package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.ProjectResourceDao;
//import com.itlize.Korera.entities.PRMPKey;
import com.itlize.Korera.entities.ProjectResource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:51 PM
 */
@Repository(value = "ProjectResourceDaoImpl")
public class ProjectResourceDaoImpl implements ProjectResourceDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addMapping(ProjectResource mapping) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            Serializable id = session.save(mapping);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int updateMapping(ProjectResource mapping) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.update(mapping);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }


    @Override
    public int saveOrUpdateMapping(ProjectResource mapping) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(mapping);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public List<ProjectResource> listMappings() {

        if(sessionFactory == null) return new ArrayList<ProjectResource>();
        Session session = sessionFactory.getCurrentSession();
        List<ProjectResource> list = new ArrayList<>();

        try{
            session.beginTransaction();
//            String sql = "select * from proj_res_mapping p join cols c " +
//                    "where p.project_id = c.project_id && " +
//                    "p.resource_id = c.resource_id";
//            Query query = session.createSQLQuery(sql);
//            List<Object[]> res = ((NativeQuery) query).addEntity("p", ProjectResource.class).addEntity("c", Cols.class).list();
//
//            for(Object[] objects : res) {
//                ProjectResource mapping = (ProjectResource) objects[0];
//                for(int i = 1; i < objects.length; i++) {
//                    mapping.getCols().add((Cols) objects[i]);
//                }
//                list.add(mapping);
//            }
            String sql = "select * from project_resource";
            list = session.createSQLQuery(sql).addEntity(ProjectResource.class).list();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<ProjectResource> getMappingById(Integer project_id, Integer resource_id) {
        if(sessionFactory == null) return  null;
        Session session = sessionFactory.getCurrentSession();
        ProjectResource mapping = null;
        List<ProjectResource> list;
        try{
            session.beginTransaction();
            String sql = "select * from project_resource where project_id=? and resource_id=?";
            Query query = session.createSQLQuery(sql);
            list = ((NativeQuery) query).addEntity(ProjectResource.class).
                    setParameter(1, project_id).setParameter(2, resource_id).list();
            mapping = list.get(0);
            session.getTransaction().commit();
            return list;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }

    }

    @Override
    public int removeMapping(ProjectResource mapping) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.remove(mapping);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }
}
