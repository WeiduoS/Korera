package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Cols;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:48 PM
 */
public interface ColsDao {

    public int addCols(Cols cols);

    public int updateCols(Cols cols);

    public int saveOrUpdateCols(Cols cols);

    public Cols getColsById(Integer id);

    public List<Cols> listCols();

    public int removeCols(Cols cols);

}
