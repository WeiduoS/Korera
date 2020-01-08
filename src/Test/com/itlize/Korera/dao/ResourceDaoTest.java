package com.itlize.Korera.dao;
import com.itlize.Korera.entities.Resource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ResourceDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    ResourceDao rd = (ResourceDao) ac.getBean("ResourceDaoImpl");
    ProjectDao pd = (ProjectDao) ac.getBean("ProjectDaoImpl");


    @Test
    public void addResourceTest() {
        Resource resource = new Resource();
        for(int i = 0; i < 5; i++) {
            resource.setResourceName("resource " + i);
            resource.setResourceCode(String.valueOf(i * 10));
            resource.setCategory_id(1);
            int res = rd.addResource(resource);
            System.out.println(res);
        }
    }

    @Test
    public void updateResourceTest() {
        Resource resource = new Resource();
        resource.setResourceId(1);
        resource.setResourceName("hello");
        resource.setCategory_id(2);
        int res = rd.updateResource(resource);
        System.out.println(res);
    }

    @Test
    public void saveOrUpdateResourceTest() {
        Resource resource = new Resource();
        resource.setResourceId(1);
        resource.setResourceName("hello11");
        resource.setCategory_id(2);
        int res = rd.updateResource(resource);
        System.out.println(res);
    }

    @Test
    public void listResourceTest() {
        List<Resource> list = rd.listResources();
        System.out.println(list);
    }

    @Test
    public void getResourceByIdTest() {
        System.out.println(rd.getResourceById(1));
    }

    @Test
    public void getResourceByNameTest() {
        List<Resource> list = rd.getResourceByName("hello11");
        System.out.println(list);
    }

    @Test
    public void removeResourceTest() {
        Resource resource = new Resource();
        resource.setResourceId(4);
        System.out.println(rd.removeResource(resource));
    }

    @Test
    public void paginationResourceTest() {
        int startIndex = 1, pageSize = 2;
        List<Resource> list = rd.paginationResource(startIndex, pageSize);
        System.out.println(list);
    }


}

