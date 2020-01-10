package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.Proj_res_mapping;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:48 PM
 */
public interface ColsDao {

    public int addCols(Cols cols);

    public int updateCols(Cols cols);

    public List<Cols> getColsById(Integer project_id, Integer resource_id);

    public int removeCols(Integer project_id, Integer resource_id);



}
