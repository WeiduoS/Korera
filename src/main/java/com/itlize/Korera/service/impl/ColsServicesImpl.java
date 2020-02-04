package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ColsDao;
import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.ProjectResource;
import com.itlize.Korera.service.ColsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/10 - 8:47 AM
 */
@Service(value = "ColsServicesImpl")
public class ColsServicesImpl implements ColsServices {

    @Autowired
    @Qualifier("ColsDaoImpl")
    ColsDao cd;

    @Autowired
    @Qualifier("ProjectResourceDaoImpl")
    ProjectResourceDao prd;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int addCols(Cols cols) {
        int res = 0;

        if(cols == null) res = -1;
        else {
            res = cd.addCols(cols);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("adding a Cols with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(cols) ]");
        }

        return res;
    }

    @Override
    public int updateCols(Cols cols) {
        int res = 0;

        if(cols == null || cols.getId() == null) res = -1;
        else {
            Cols colsById = cd.getColsById(cols.getId());
            if(colsById == null) res = -2;
            else{
                res = cd.updateCols(cols);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("updating a Cols with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for no such cols(id). ]");
        }

        return res;
    }

    @Override
    public int saveOrUpdateCols(Cols cols) {
        int res = 0;

        if(cols == null) res = -1;
        else{
            res = cd.saveOrUpdateCols(cols);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("saveOrUpdate a Cols with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(cols). ]");
        }

        return res;
    }

    @Override
    public Cols getColsById(Integer id) {
        Cols cols;
        if(id == null) return null;
        else{
            cols = cd.getColsById(id);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Cols by id with result: " + (cols == null ? " null" : cols));
        }

        return cols;
    }

    @Override
    public int removeCols(Cols cols) {
        int res = 0;

        if(cols == null || cols.getId() == null) res = -1;
        else{
            if(cols.getProjectResource() != null) cols.setProjectResource(null);
            res = cd.removeCols(cols);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("delete a Cols with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(cols or id) ]");
        }

        return res;
    }

}
