package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Project;

import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 3:57 PM
 */
public interface PorjectDao {

    public List<Project> findAll();

    public int savePoject(Project project);
}
