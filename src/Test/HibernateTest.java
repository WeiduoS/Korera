
import com.itlize.Korera.entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "select project_id, project_name, user_id from Project";
        Query query = session.createSQLQuery(hql);
        List<Project> list = ((NativeQuery) query).addEntity(Project.class).list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }
}
