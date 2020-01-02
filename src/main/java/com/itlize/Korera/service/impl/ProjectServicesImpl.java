package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ProjectDao;
import com.itlize.Korera.service.ProjectServices;
import com.itlize.Korera.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 4:00 PM
 */
@Service("ProjectServicesImpl")
public class ProjectServicesImpl implements ProjectServices {

    @Autowired
    @Qualifier("ProjectDaoImpl")
    ProjectDao pd;

    @Override
    public int addProject(Project project) {
        if(project == null) return -1;
        int res = pd.addProject(project);
        return res;
    }

    @Override
    public int updateProject(Project project) {
        if(project == null) return -1;
        int res = pd.updateProject(project);
        return res;
    }

    @Override
    public int saveOrUpdateProject(Project project) {
        if(project == null) return -1;
        int res = pd.saveOrUpdateProject(project);
        return res;
    }

    @Override
    public List<Project> listProjects() {
        List<Project> res = pd.listProjects();
        return res == null ? new ArrayList<>() : res;
    }

    @Override
    public Project getProjectById(Integer id) {
        if(id == null || id < 0) return null;
        Project res = pd.getProjectById(id);
        return res;
    }

    @Override
    public List<Project> getProjectByName(String project_name) {
        if(project_name == null) return null;
        List<Project> res = pd.getProjectByName(project_name);
        return res == null ? new ArrayList<>() : res;
    }

    @Override
    public int removeProject(Project project) {
        if(project == null) return -1;
        int res = pd.removeProject(project);
        return res;
    }

    @Override
    public List<Project> paginationProject(Integer startIndex, Integer pageSize) {
        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) return new ArrayList<>();
        List<Project> res = pd.paginationProject(startIndex, pageSize);
        return res == null ? new ArrayList<>() : res;
    }
}
