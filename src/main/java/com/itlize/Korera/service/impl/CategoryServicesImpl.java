package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.CategoryDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.service.CategoryServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int addCategory(Category category) {
        int res = 0;

        if(category == null) res = -1;
        else {
            List<Category> list = cd.getCategoryByName(category.getCategory_name());
            if(!list.isEmpty()) res = -2;
            else{
                res = cd.addCategory(category);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("adding a Category with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for duplicated. ]");
        }

        return res;
    }

    @Override
    public int updateCategory(Category category) {
        int res = 0;

        if(category == null || category.getCategory_id() == null) res = -1;
        else {
            Category categoryById = cd.getCategoryById(category.getCategory_id());
            if(categoryById == null) res = -2;
            else{
                res = cd.updateCategory(category);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("updating a Category with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for no such category(id). ]");
        }

        return res;
    }

    @Override
    public int saveOrUpdateCategory(Category category) {
        int res = 0;

        if(category == null || category.getCategory_name() == null) res = -1;
        else{
            List<Category> list = cd.getCategoryByName(category.getCategory_name());
            if(!list.isEmpty()) res = -2;
            else{
                res = cd.saveOrUpdateCategory(category);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("saveOrUpdate a Category with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for duplicated(category name). ]");
        }

        return res;
    }

    @Override
    public List<Category> listCategories() {
        List<Category> list = cd.listCategories();

        int res = list.size();

        if(logger.isDebugEnabled()) {
            logger.debug("list all Category with size " + res);
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category;
        if(id == null) return null;
        else{
            category = cd.getCategoryById(id);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Category by id with result: " + (category == null ? " null" : category));
        }

        return category;
    }

    @Override
    public List<Category> getCategoryByName(String category_name) {
        List<Category> list;
        if(category_name == null) list = null;
        else{
            list = cd.getCategoryByName(category_name);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Category by name with size " + (list == null ? 0 : list.size()));
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public int removeCategories(Category category) {
        int res = 0;

        if(category == null || category.getCategory_id() == null) res = -1;
        else{
            res = cd.removeCategories(cd.getCategoryById(category.getCategory_id()));
        }

        if(logger.isDebugEnabled()) {
            logger.debug("delete a Category with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(category or id) ]");
        }

        return res;
    }

    @Override
    public BigInteger getCategorySize() {
        BigInteger res = cd.getCategorySize();

        if(logger.isDebugEnabled()) {
            logger.debug("get Category size with result " + res);
        }

        return res;
    }

    @Override
    public List<Category> paginationCategory(Integer startIndex, Integer pageSize) {

        List<Category> list;
        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) list = null;
        else{
            list = cd.paginationCategory(startIndex, pageSize);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Category by pagination with size " + (list == null ? 0 : list.size()));
        }

        return list;
    }
}
