package com.itlize.Korera.service;

import com.itlize.Korera.entities.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/8 - 3:24 PM
 */

@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceTest {

    @Autowired
    @Qualifier("CategoryServicesImpl")
    CategoryServices cs;

    @Test
    public void addCategoryTest() {
        Category category = new Category();
        for(int i = 0; i < 10; i++) {
            category.setCategory_name("category: " + i);
            int res = cs.addCategory(category);
            System.out.println(res);
        }
    }

    @Test
    public void updateCategoryTest() {
        Category category = new Category();
        category.setCategory_id(1);
        category.setCategory_name("hello 01");
        int res = cs.updateCategory(category);
        System.out.println(res);
    }

    @Test
    public void saveOrUpdateCategoryTest() {
        Category category = new Category();
        category.setCategory_name("hello 01");
        int res = cs.saveOrUpdateCategory(category);
        System.out.println(res);
    }

    @Test
    public void listCategoriesTest() {
        List<Category> list = cs.listCategories();
        System.out.println(list);
    }

    @Test
    public void getCategoryByIdTest() {
        Category category = cs.getCategoryById(1);
        System.out.println(category.toString());
    }

    @Test
    public void getCategoryByNameTest() {
        List<Category> list = cs.getCategoryByName("category: 1");
        System.out.println(list);
    }

    @Test
    public void removeCategoryTest() {
        Category category = new Category();
        category.setCategory_id(11);
        category.setCategory_name("hello 01");
        int res = cs.removeCategories(category);
        System.out.println(res);
    }

    @Test
    public void paginationCategoryTest() {
        int pageIndex = 1, pageSize = 3;
        List<Category> list = cs.paginationCategory(pageIndex, pageSize);
        System.out.println(list);
    }

}
