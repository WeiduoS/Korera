package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ColsDao;
import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.ProjectResource;
import com.itlize.Korera.service.ColsServices;
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

    @Override
    public int addCols(Cols cols) {
        if(cols == null) return -1;
        return cd.addCols(cols);
    }

    @Override
    public int updateCols(Cols cols) {
        if(cols == null) return -1;
        return cd.updateCols(cols);
    }

    @Override
    public int saveOrUpdateCols(Cols cols) {
        if(cols == null) return -1;
        return cd.saveOrUpdateCols(cols);
    }

    @Override
    public Cols getColsById(Integer id) {
        if(id == null || id < 0) return null;
        return cd.getColsById(id);
    }

    @Override
    public int removeCols(Cols cols) {
        if(cols == null ) return -1;
        return cd.removeCols(cols);
    }

}
