package com.itlize.Korera.service.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.service.CategoryServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/29 - 11:17 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@Transactional
public class CategoryServicesImplTest {

    @Autowired
    @Qualifier(value = "CategoryServicesImpl")
    CategoryServices categoryServices;

    @Test
    public void addCategory() {
        for(int i = 0; i < 10; i++) {
            Category category = new Category(i + 10, i + 10 + "");
            int res = categoryServices.addCategory(category);
            System.out.println(res);
        }
    }

    @Test
    public void updateCategory() {
        Category category = new Category(10, "sfdafdf");
        int res = categoryServices.updateCategory(category);
        System.out.println(res);
    }

    @Test
    public void saveOrUpdateCategory() {
        Category category = new Category(11, "sfdafdfsdfasdf");
        int res = categoryServices.saveOrUpdateCategory(category);
        System.out.println(res);
    }

    @Test
    public void listCategories() {

        List<Category> categoryList = categoryServices.listCategories();

        System.out.println(categoryList);
    }

    @Test
    public void getCategoryById() {
    }

    @Test
    public void getCategoryByName() {
    }

    @Test
    public void removeCategories() {
        int res = categoryServices.removeCategories(categoryServices.getCategoryById(2));
        System.out.println("res: " + res);
    }

    @Test
    public void getCategorySize() {
    }

    @Test
    public void paginationCategory() {
    }
}