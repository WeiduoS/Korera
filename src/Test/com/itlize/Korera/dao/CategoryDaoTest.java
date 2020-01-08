package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Category;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/8 - 2:02 PM
 */
public class CategoryDaoTest {

    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    CategoryDao cd = (CategoryDao) ac.getBean("CategoryDaoImpl");

    @Test
    public void addCategoryTest() {
        Category category = new Category();
        for(int i = 0; i < 10; i++) {
            category.setCategory_name("category: " + i);
            int res = cd.addCategory(category);
            System.out.println(res);
        }
    }

    @Test
    public void updateCategoryTest() {
        Category category = new Category();
        category.setCategory_id(1);
        category.setCategory_name("hello 01");
        int res = cd.updateCategory(category);
        System.out.println(res);
    }

    @Test
    public void saveOrUpdateCategoryTest() {
        Category category = new Category();
        category.setCategory_name("hello 01");
        int res = cd.saveOrUpdateCategory(category);
        System.out.println(res);
    }

    @Test
    public void listCategoriesTest() {
        List<Category> list = cd.listCategories();
        System.out.println(list);
    }

    @Test
    public void getCategoryByIdTest() {
        Category category = cd.getCategoryById(1);
        System.out.println(category.toString());
    }

    @Test
    public void getCategoryByNameTest() {
        List<Category> list = cd.getCategoryByName("category: 1");
        System.out.println(list);
    }

    @Test
    public void removeCategoryTest() {
        Category category = new Category();
        category.setCategory_id(11);
        category.setCategory_name("hello 01");
        int res = cd.removeCategories(category);
        System.out.println(res);
    }

    @Test
    public void paginationCategoryTest() {
        int pageIndex = 1, pageSize = 3;
        List<Category> list = cd.paginationCategory(pageIndex, pageSize);
        System.out.println(list);
    }

}
