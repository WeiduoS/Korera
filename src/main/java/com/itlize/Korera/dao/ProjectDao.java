package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Project;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 3:57 PM
 */
public interface ProjectDao {

    public int addProject(Project project);

    public int updateProject(Project project);

    public int saveOrUpdateProject(Project project);

    public List<Project> listProjects();

    public Project getProjectById(Integer id);

    public List<Project> getProjectByName(String project_name);

    public int removeProject(Integer project_id);

    public BigInteger getProjectSize();

    public List<Project> paginationProject(Integer startIndex, Integer pageSize);

}
