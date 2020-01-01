import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Weiduo
 * @date 2019/12/31 - 1:58 PM
 */
public class SpringTest {

    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
        System.out.println(ac.getBean("ProjectServices"));
    }
}
