
import com.fasterxml.classmate.AnnotationConfiguration;
import com.itlize.Korera.entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/31 - 4:38 PM
 */
public class HibernateTest {
    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
        SessionFactory factory = (SessionFactory) ac.getBean("sessionFactory");
        Session session = factory.getCurrentSession();
        System.out.println("session1: " + session);
        session.beginTransaction();
        String hql = "select * from Project";
        Query query = session.createSQLQuery(hql);
//        System.out.println("result list: " + resultList.toArray().toString());
        List<Project> list = ((NativeQuery) query).addEntity(Project.class).list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void test2() {
        Configuration configuration = new Configuration().configure("config/hibernate.cfg.xml");
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        System.out.println("session1: " + session);
        session.beginTransaction();
        String hql = "select * from Project";
        Query query = session.createSQLQuery(hql);
        List<Project> list = ((NativeQuery) query).addEntity(Project.class).list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();

    }
}
