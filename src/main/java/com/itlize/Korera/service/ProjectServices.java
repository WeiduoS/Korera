package com.itlize.Korera.service;

import com.itlize.Korera.entities.Project;

import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 3:59 PM
 */
public interface ProjectServices {
    public int addProject(Project project);

    public int updateProject(Project project);

    public int saveOrUpdateProject(Project project);

    public List<Project> listProjects();

    public Project getProjectById(Integer id);

    public List<Project> getProjectByName(String project_name);

    public int removeProject(Integer project_id);

    public List<Project> paginationProject(Integer startIndex, Integer pageSize);
}
