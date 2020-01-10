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
        List<Project> list = new ArrayList<>();

        try{
            session.beginTransaction();
//            String sql = "select p.project_id, p.project_name, p.user_id, r.resource_id, r.resource_code, r.resource_name, r.category_id from Project p " +
//                    "join proj_res_mapping prm " +
//                    "on p.project_id = prm.project_id " +
//                    "join Resource r " +
//                    "on r.resource_id = prm.resource_id";
            String sql = "select * from Project";
            Query query = session.createSQLQuery(sql);
            list = (List<Project>) ((NativeQuery) query).addEntity(Project.class).list();
//            List<Object[]> res = ((NativeQuery) query).addEntity(Project.class).addEntity(Resource.class).list();
//
//            for(Object[] objects : res) {
//                Project project = (Project) objects[0];
//                for(int i = 1; i < objects.length; i++) {
//                    project.getResouces().add((Resource) objects[i]);
//                }
//                list.add(project);
//            }
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }

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
            return project;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Project getProject(Integer id) {
        if(sessionFactory == null) return null;
        Session session = sessionFactory.getCurrentSession();
        Project project = null;
        try{
            session.beginTransaction();
            String sql = "select p.project_id, p.project_name, p.user_id, r.resource_id, r.resource_code, r.resource_name, r.category_id from Project p " +
                    "join proj_res_mapping prm " +
                    "on ? = prm.project_id " +
                    "join Resource r " +
                    "on r.resource_id = prm.resource_id";
            Query query = session.createSQLQuery(sql);
            List<Object[]> res = ((NativeQuery) query).addEntity(Project.class).addEntity(Resource.class).list();
            for(Object[] objects : res) {
                project = (Project) objects[0];
                for(int i = 1; i < objects.length; i++) {
                    project.getResouces().add((Resource) objects[i]);
                }
            }
            session.getTransaction().commit();
            return project;
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
        List<Project> list = null;
        try{
            session.beginTransaction();
            String sql = "select * from Project where project_name=?";
            Query query = session.createSQLQuery(sql).setParameter(1, project_name);
            list = ((NativeQuery) query).addEntity(Project.class).list();
            session.getTransaction().commit();
            return list;
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
//            String sql = "delete from Project where project_id=?";
//            Query query = session.createSQLQuery(sql).setParameter(1, project_id);
//            int res = query.executeUpdate();
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
