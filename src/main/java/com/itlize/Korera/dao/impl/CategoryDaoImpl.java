package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.CategoryDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2020/1/8 - 1:55 PM
 */

@Repository(value = "CategoryDaoImpl")
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addCategory(Category category) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int updateCategory(Category category) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int saveOrUpdateCategory(Category category) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(category);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public List<Category> listCategories() {
        if(sessionFactory == null) return new ArrayList<Category>();
        Session session = sessionFactory.getCurrentSession();
        List<Category> list = new ArrayList<>();
        try{
            session.beginTransaction();
            String sql = "select * from Category c join Resource r on c.category_id = r.category_id";
            Query query = session.createSQLQuery(sql);
            List<Object[]> res = ((NativeQuery) query).addEntity(Category.class).addEntity(Resource.class).list();

            for(Object[] objects : res) {
                Category category = (Category) objects[0];
                Set<Resource> set = new HashSet<>();
                for(int i = 1; i < objects.length; i++) {
                    set.add((Resource) objects[i]);
                }
                category.setResources(set);
                list.add(category);
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
    public Category getCategoryById(Integer id) {
        if(sessionFactory == null) return null;
        Session session = sessionFactory.getCurrentSession();
        Category category = null;
        try{
            session.beginTransaction();
            category = session.get(Category.class, id);
            session.getTransaction().commit();
            return category;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Category> getCategoryByName(String category_name) {
        if(sessionFactory == null) return new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        List<Category> list = null;
        try{
            session.beginTransaction();
            String sql = "select * from Category where category_name=?";
            Query query = session.createSQLQuery(sql).setParameter(1, category_name);
            list = ((NativeQuery) query).addEntity(Category.class).list();
            session.getTransaction().commit();
            return list;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
    }

    @Override
    public int removeCategories(Category category) {
        if(sessionFactory == null) return -1;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.remove(category);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public List<Category> paginationCategory(Integer startIndex, Integer pageSize) {
        if(sessionFactory == null) return new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        List<Category> list;
        try{
            session.beginTransaction();
            int limit = (startIndex - 1) * pageSize;
            String sql = "select * from Category";
            Query query = session.createSQLQuery(sql).setFirstResult(limit).setMaxResults(pageSize);
            list = ((NativeQuery) query).addEntity(Category.class).list();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
            return new ArrayList<>();
        }
        return list;
    }
}
