package com.itlize.Korera.dao.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.dao.CategoryDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/28 - 3:10 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
public class CategoryDaoImplTest {

    @Autowired
    @Qualifier(value = "CategoryDaoImpl")
    CategoryDao cd;

    @Test
    public void addCategory() {
        Category category = new Category();
        for(int i = 0; i < 10; i++) {
            category.setCategory_name("category: " + i);
            Set<Resource> set = new HashSet<>();

            for(int j = 0; j < 3; j++) {
                set.add(new Resource("000", "000", category));
            }
            category.setResources(set);
            int res = cd.addCategory(category);
            System.out.println(res);
        }
    }

    @Test
    public void updateCategory() {

        Category category = new Category(11, "432sfa");


    }

    @Test
    public void saveOrUpdateCategory() {
        Category category = new Category(1, "1000");

        for(int i = 1; i <= 1; i++) {
            Set<Resource> set = new HashSet<>();

            for(int j = 1; j <= 3; j++) {
                set.add(new Resource(j, "0002", "0002", category));
            }

            for(int j = 1; j <= 3; j++) {
                set.add(new Resource( "0001", "0001", category));
            }

            category.setResources(set);
            int res = cd.saveOrUpdateCategory(category);
            System.out.println(res);
        }
    }

    @Test
    public void listCategories() {
        List<Category> list = cd.listCategories();
        System.out.println(list);
    }

    @Test
    public void getCategoryById() {
        Category category = cd.getCategoryById(1);
        System.out.println(category.toString());
    }

    @Test
    public void getCategoryByName() {
        List<Category> list = cd.getCategoryByName("1000");
        System.out.println(list);
    }

    @Test
    public void removeCategories() {
        cd.removeCategories(cd.getCategoryById(33));
    }

    @Test
    public void getCategorySize() {
        BigInteger res = cd.getCategorySize();
        System.out.println(res);
    }

    @Test
    public void paginationCategory() {
        int pageIndex = 1, pageSize = 3;
        List<Category> list = cd.paginationCategory(pageIndex, pageSize);
        System.out.println(list);
    }
}