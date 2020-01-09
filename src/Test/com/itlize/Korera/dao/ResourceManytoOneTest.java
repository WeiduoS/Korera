package com.itlize.Korera.dao;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import com.mysql.cj.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ResourceManytoOneTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    ResourceDao rd = (ResourceDao) ac.getBean("ResourceDaoImpl");
    //ProjectDao pd = (ProjectDao) ac.getBean("ProjectDaoImpl");

    @Test
    public void addResourceTest(){
        //Project project = new Project("testmanytoone");
        Resource resource1 = new Resource("11111","resource01");
        rd.addResource(resource1);
    }

    @Test
    public void updateResource(){

        Resource r_new = new Resource("dynamic01ss","dynamicsss");
        r_new.setResourceId(136);

//        r_new.setResourceCode("dynamic01ss");

        rd.updateResource(r_new);
    }


}

