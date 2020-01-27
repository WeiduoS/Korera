package com.itlize.Korera.dao.impl;

import com.itlize.Korera.entities.JWTRemeberToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

/**
 * @author Weiduo
 * @date 2020/1/26 - 3:55 PM
 */
public class JWTDaoImpl implements PersistentTokenRepository {

    private SessionFactory sessionFactory;

    @Override
    public void createNewToken(PersistentRememberMeToken token){
        if(sessionFactory == null) return;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(token);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return null;
    }

    @Override
    public void removeUserTokens(String username) {

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
