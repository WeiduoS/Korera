package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ColsDao;
import com.itlize.Korera.entities.Cols;
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
@Service(value = "ColsServiceImpl")
public class ColsServiceImpl implements ColsServices {

    @Autowired
    @Qualifier("ColsDaoImpl")
    ColsDao cd;

    @Override
    public int addCols(Cols cols) {
        return 0;
    }

    @Override
    public int updateCols(Cols cols) {
        return 0;
    }

    @Override
    public List<Cols> getColsById(Integer project_id, Integer resource_id) {
        if(project_id == null || resource_id == null || project_id < 0 || resource_id < 0) return new ArrayList<>();
        return cd.getColsById(project_id, resource_id);
    }

    @Override
    public int removeCols(Integer project_id, Integer resource_id) {
        return 0;
    }

}
