package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.CategoryDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/8 - 3:14 PM
 */

@Service("CategoryServicesImpl")
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    @Qualifier("CategoryDaoImpl")
    CategoryDao cd;

    @Override
    public int addCategory(Category category) {
        if(category == null) return -1;
        int res = cd.addCategory(category);
        return res;
    }

    @Override
    public int updateCategory(Category category) {
        if(category == null) return -1;
        return cd.updateCategory(category);
    }

    @Override
    public int saveOrUpdateCategory(Category category) {
        if(category == null) return -1;
        return cd.saveOrUpdateCategory(category);
    }

    @Override
    public List<Category> listCategories() {
        List<Category> list = cd.listCategories();
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public Category getCategoryById(Integer id) {
        if(id == null) return null;
        return cd.getCategoryById(id);
    }

    @Override
    public List<Category> getCategoryByName(String category_name) {
        if(category_name == null) return new ArrayList<>();
        List<Category> list = cd.getCategoryByName(category_name);
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public int removeCategories(Category category) {
        if(category == null) return -1;
        return cd.removeCategories(category);
    }

    @Override
    public BigInteger getCategorySize() {
        return cd.getCategorySize();
    }

    @Override
    public List<Category> paginationCategory(Integer startIndex, Integer pageSize) {
        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) return new ArrayList<>();
        List<Category> list = cd.paginationCategory(startIndex, pageSize);
        return list == null ? new ArrayList<>() : list;
    }
}
