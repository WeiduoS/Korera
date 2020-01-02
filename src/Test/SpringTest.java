import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Weiduo
 * @date 2019/12/31 - 1:58 PM
 */
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

//    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
        System.out.println(ac.getBean("ProjectServices"));
    }
}
