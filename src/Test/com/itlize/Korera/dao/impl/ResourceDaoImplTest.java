package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.ProjectDao;
import com.itlize.Korera.dao.ResourceDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/22 - 3:30 PM
 */
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ResourceDaoImplTest {

    @Autowired
    @Qualifier(value = "ResourceDaoImpl")
    ResourceDao rd;

    @Autowired
    @Qualifier(value = "ProjectDaoImpl")
    ProjectDao pd;


    @Test
    public void addResource() {
        Resource resource = new Resource();
        for(int i = 1; i <= 1; i++) {
            resource.setResource_name("new resource " + i);
            resource.setResource_code(String.valueOf(i * 10));
            resource.setCategory_id(new Category(2, " 000"));

            Set<Project> set = new HashSet<>();
            set.add(new Project(40));
            resource.setProjects(set);

            int res = rd.addResource(resource);
            System.out.println(res);
        }
    }

    @Test
    public void updateResource() {
        Resource resource = new Resource();
        resource.setResource_id(42);
        resource.setResource_name("hello");
        resource.setCategory_id(new Category(2));
        int res = rd.updateResource(resource);
        System.out.println(res);
    }

    @Test
    public void saveOrUpdateResource() {
        Resource resource = new Resource();
        resource.setResource_id(1);
        resource.setResource_name("hello11");
        resource.setCategory_id(new Category(1));
        int res = rd.updateResource(resource);
        System.out.println(res);
    }

    @Test
    public void listResources() {
        List<Resource> list = rd.listResources();
        System.out.println(list);
    }

    @Test
    public void getResourceById() {
        System.out.println(rd.getResourceById(5));
    }

    @Test
    public void getResourceByName() {
        List<Resource> list = rd.getResourceByName("hello11");
        System.out.println(list);
    }

    @Test
    public void getResourceSize() {
        BigInteger res = rd.getResourceSize();
        System.out.println(res);
    }

    @Test
    public void removeResource() {
        System.out.println(rd.removeResource(rd.getResourceById(42)));
    }

    @Test
    public void paginationResource() {
        int startIndex = 1, pageSize = 2;
        List<Resource> list = rd.paginationResource(startIndex, pageSize);
        System.out.println(list);
    }
}