package com.itlize.Korera.service;

import com.itlize.Korera.entities.Cols;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/10 - 8:47 AM
 */
public interface ColsServices {

    public int addCols(Cols cols);

    public int updateCols(Cols cols);

    public int saveOrUpdateCols(Cols cols);

    public Cols getColsById(Integer id);

    public int removeCols(Cols cols);
}
