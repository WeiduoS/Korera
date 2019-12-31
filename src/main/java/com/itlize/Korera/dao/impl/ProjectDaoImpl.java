package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.PorjectDao;
import com.itlize.Korera.entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/31 - 4:27 PM
 */
@Repository
public class ProjectDaoImpl implements PorjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM mydb.Project";
        Query query = session.createQuery(hql);
        List<Project> res = query.list();
        return res;
    }

    @Override
    public int savePoject(Project project) {
        return 0;
    }
}
