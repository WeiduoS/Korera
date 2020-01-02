package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.ProjectDao;
import com.itlize.Korera.entities.Project;
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
 * @date 2019/12/31 - 4:27 PM
 */
@Repository(value = "ProjectDaoImpl")
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addProject(Project project) {
        System.out.println(sessionFactory);
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(project);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public int updateProject(Project project) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.update(project);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public int saveOrUpdateProject(Project project) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(project);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public List<Project> listProjects() {
        if(sessionFactory == null) return new ArrayList<Project>();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String sql = "select * from Project";
        Query query = session.createSQLQuery(sql);
        List<Project> list = ((NativeQuery) query).addEntity(Project.class).list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public Project getProjectById(Integer id) {
        if(sessionFactory == null) return null;
        Session session = sessionFactory.getCurrentSession();
        Project project = null;
        try{
            session.beginTransaction();
            project = session.get(Project.class, id);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return project;
    }

    @Override
    public List<Project> getProjectByName(String project_name) {
        if(sessionFactory == null) return new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        List<Project> list = null;
        try{
            session.beginTransaction();
            String sql = "select * from Project where project_name=?";
            Query query = session.createSQLQuery(sql).setParameter(1, project_name);
            list = ((NativeQuery) query).addEntity(Project.class).list();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public int removeProject(Project project) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.delete(project);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
        return 1;
    }

    @Override
    public List<Project> paginationProject(Integer startIndex, Integer pageSize) {
        if(sessionFactory == null) return new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        List<Project> list;
        try{
            session.beginTransaction();
            int limit = (startIndex - 1) * pageSize;
            String sql = "select * from Project";
            Query query = session.createSQLQuery(sql).setFirstResult(limit).setMaxResults(pageSize);
            list = ((NativeQuery) query).addEntity(Project.class).list();
            System.out.println("list: " + list);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
        return list;
    }
}
