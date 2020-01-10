package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.Proj_res_mappingDao;
import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.Proj_res_mapping;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
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
 * @date 2020/1/9 - 3:51 PM
 */
@Repository(value = "ProjectResourceDaoImpl")
public class Proj_res_mappingDaoImpl implements Proj_res_mappingDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addMapping(Proj_res_mapping mapping) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(mapping);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int updateMapping(Proj_res_mapping mapping) {
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
    public List<Proj_res_mapping> listMappings() {

        if(sessionFactory == null) return new ArrayList<Proj_res_mapping>();
        Session session = sessionFactory.getCurrentSession();
        List<Proj_res_mapping> list = new ArrayList<>();

        try{
            session.beginTransaction();
            String sql = "select * from proj_res_mapping p join cols c " +
                    "where p.project_id = c.project_id && " +
                    "p.resource_id = c.resource_id";
            Query query = session.createSQLQuery(sql);
            List<Object[]> res = ((NativeQuery) query).addEntity("p", Proj_res_mapping.class).addEntity("c", Cols.class).list();

            for(Object[] objects : res) {
                Proj_res_mapping mapping = (Proj_res_mapping) objects[0];
                for(int i = 1; i < objects.length; i++) {
                    mapping.getCols().add((Cols) objects[i]);
                }
                list.add(mapping);
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public Proj_res_mapping getMappingById(Integer project_id, Integer resource_id) {
        if(sessionFactory == null) return  null;
        Session session = sessionFactory.getCurrentSession();
        Proj_res_mapping mapping = null;

        try{
            session.beginTransaction();
            String sql = "select * from proj_res_mapping where project_id=? and resource_id=?";
            Query query = session.createSQLQuery(sql);
            List<Proj_res_mapping> list = (List<Proj_res_mapping>) ((NativeQuery) query).addEntity(Proj_res_mapping.class).
                    setParameter(1, project_id).setParameter(2, resource_id).list();
            mapping = list.get(0);
            session.getTransaction().commit();
            return mapping;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }

    }

    @Override
    public int removeMapping(Proj_res_mapping mapping) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
//            String sql = "delete from proj_res_mapping where project_id=? and resource_id=?";
////            Query query = session.createSQLQuery(sql)
////                    .setParameter(1, mapping.getPrmpKey().getProject_id())
////                    .setParameter(2, mapping.getPrmpKey().getResource_id());
////
////            int res = query.executeUpdate();
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
