package com.itlize.Korera.service.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.service.ResourceServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/29 - 1:01 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@Transactional
public class ResourceServicesImplTest {


    @Autowired
    @Qualifier(value = "ResourceServicesImpl")
    ResourceServices resourceServices;

    @Test
    public void addResource() {
        for(int i = 0; i < 5; i++) {
            Resource resource = new Resource();
            resource.setResource_name("resource " + i);
            resource.setResource_code(String.valueOf(i * 10));
            resource.setCategory_id(new Category(2));
            int res = resourceServices.addResource(resource);
            System.out.println(res);
        }
    }

    @Test
    public void updateResource() {
        Resource resource = new Resource(32, "40", "resource 4 new", new Category(2));

        System.out.println(resourceServices.updateResource(resource));
    }

    @Test
    public void saveOrUpdateResource() {

        Resource resource = new Resource(32, "40", "resource 4 new", new Category(2));

        System.out.println(resourceServices.saveOrUpdateResource(resource));
    }

    @Test
    public void listResources() {
        List<Resource> list = resourceServices.listResources();
        System.out.println(list);
    }

    @Test
    public void getResourceById() {
        System.out.println(resourceServices.getResourceById(1));
    }

    @Test
    public void getResourceByName() {
        List<Resource> list = resourceServices.getResourceByName("hello11");
        System.out.println(list);
    }

    @Test
    public void removeResource() {
        Resource resource = new Resource();
        resource.setResource_id(37);
        System.out.println(resourceServices.removeResource(resource));
    }

    @Test
    public void getResourceSize() {
        BigInteger res = resourceServices.getResourceSize();
        System.out.println(res);
    }

    @Test
    public void paginationResource() {
        int startIndex = 1, pageSize = 2;
        List<Resource> list = resourceServices.paginationResource(startIndex, pageSize);
        System.out.println(list);
    }
}