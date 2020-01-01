package com.itlize.Korera.service;

import com.itlize.Korera.entities.Project;

import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 3:59 PM
 */
public interface ProjectServices {
    public List<Project> findAll();

    public int savePoject(Project project);
}
