package com.itlize.Korera.service;

import com.itlize.Korera.entities.Category;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/8 - 3:13 PM
 */
public interface CategoryServices {

    public int addCategory(Category category);

    public int updateCategory(Category category);

    public int saveOrUpdateCategory(Category category);

    public List<Category> listCategories();

    public Category getCategoryById(Integer id);

    public List<Category> getCategoryByName(String project_name);

    public int removeCategories(Category category);

    public List<Category> paginationCategory(Integer startIndex, Integer pageSize);
}
