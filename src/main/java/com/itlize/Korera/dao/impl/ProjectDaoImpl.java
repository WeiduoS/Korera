package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.ProjectDao;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import javafx.beans.binding.ObjectExpression;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.attach.VirtualMachine.list;

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
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(project);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int updateProject(Project project) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.update(project);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int saveOrUpdateProject(Project project) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(project);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public List<Project> listProjects() {
        if(sessionFactory == null) return new ArrayList<Project>();
        Session session = sessionFactory.getCurrentSession();
        List<Project> list;

        try{
            session.beginTransaction();
            String sql = "select * from Project";
            Query query = session.createSQLQuery(sql);
            list = (List<Project>) ((NativeQuery) query).addEntity(Project.class).list();
            session.getTransaction().commit();
            return list == null ? new ArrayList<>() : list;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
    }


    @Override
    public Project getProjectById(Integer id) {
        if(sessionFactory == null) return null;
        Session session = sessionFactory.getCurrentSession();
        Project project;
        try{
            session.beginTransaction();
            project = session.get(Project.class, id);
            session.getTransaction().commit();
            return project == null ? new Project() : project;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }



    @Override
    public List<Project> getProjectByName(String project_name) {
        if(sessionFactory == null) return new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        List<Project> list;
        try{
            session.beginTransaction();
            String sql = "select * from Project where project_name=?";
            Query query = session.createSQLQuery(sql).setParameter(1, project_name);
            list = ((NativeQuery) query).addEntity(Project.class).list();
            session.getTransaction().commit();
            return list == null ? new ArrayList<>() : list;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
    }

    @Override
    public int removeProject(Project project) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.remove(project);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public BigInteger getProjectSize() {
        if(sessionFactory == null) return BigInteger.valueOf(-1);
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            String sql = "select count(project_id) from Project";
            Query query = session.createSQLQuery(sql);
            List<BigInteger> list = query.list();
            session.getTransaction().commit();
            return list.get(0);
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return BigInteger.valueOf(-1);
        }
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
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
        return list;
    }
}
