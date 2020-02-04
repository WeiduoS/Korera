package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ProjectDao;
import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.entities.*;
import com.itlize.Korera.service.ProjectServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2019/12/30 - 4:00 PM
 */
@Service("ProjectServicesImpl")
public class ProjectServicesImpl implements ProjectServices {

    @Autowired
    @Qualifier("ProjectDaoImpl")
    ProjectDao pd;

    @Autowired
    @Qualifier("ProjectResourceDaoImpl")
    ProjectResourceDao prd;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int addProject(Project project) {
        int res = 0;

        if(prd == null || pd == null || project == null) res = -1;
        else{
            res = pd.addProject(project);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("adding a Project with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer ]");
        }

        return res;
    }


    @Override
    public int updateProject(Project project) {
        int res = 0;

        if(project == null || project.getProject_id() == null) res = -1;
        else {
            Project projectById = pd.getProjectById(project.getProject_id());
            if(projectById == null) res = -2;
            else{
                res = pd.updateProject(project);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("updating a Project with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for no such project(id). ]");
        }

        return res;
    }

    @Override
    public int saveOrUpdateProject(Project project) {
        if(project == null) return -1;
        int res = 0;
        if(project == null) res = -2;
        else {
            // remove the parent foreign key constraint, the foreign key not for updating, delete by child objects
            if(project.getUser() != null) project.setUser(null);

            for(Resource resource : project.getResouces()) {
                // prepare the project resource mapping before updating the project
                List<ProjectResource> prs = prd.getMappingById(project.getProject_id(), resource.getResource_id());
                for(ProjectResource pr : prs) {
                    if(resource.getCols() != null) {

                        // prepare the project resource mapping's cols for the certain project resource mapping
                        List<Cols> persistentColsList = new ArrayList<>();

                        Set<Cols> dbCols = new HashSet<>(pr.getCols());

                        // remove the new input cols that the database already exists, because the new input with their id meaning the cols should be update
                        for(Cols col : resource.getCols()) {
                            if(dbCols.contains(col)) dbCols.remove(col);
                        }

                        persistentColsList.addAll(dbCols);
                        persistentColsList.addAll(resource.getCols());

                        for(Cols cols : persistentColsList) cols.setProjectResource(pr);

                        pr.setCols(persistentColsList);

                    }

                    res = prd.saveOrUpdateMapping(pr);
                    if(res < 0) {

                        if(logger.isDebugEnabled()) {
                            logger.debug("saving or updating a Project by updating project resource mapping in advance with result " + res + ", [ 1 for success, 0 for unknown error, -1 for failed]");
                        }

                        return -1;
                    }
                }
            }
        }

        res = pd.saveOrUpdateProject(project);

        if(logger.isDebugEnabled()) {
            logger.debug("saving or updating a Project with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(project) ]");
        }

        return res;
    }

    @Override
    public List<Project> listProjects() {
        List<Project> list = pd.listProjects();

        int res = list.size();

        if(logger.isDebugEnabled()) {
            logger.debug("list all Project with size " + res);
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public Project getProjectById(Integer id) {
        Project project;

        if(id == null) return null;
        else{
            project = pd.getProjectById(id);
            if(project.getResouces() != null) {
                for(Resource res : project.getResouces()) {

                    List<ProjectResource> prs = prd.getMappingById(
                            project.getProject_id(),
                            res.getResource_id());

                    if(res.getCols() == null) res.setCols(new ArrayList<>());

                    for(ProjectResource pr : prs) {
                        res.getCols().addAll(pr.getCols());
                    }
                }
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Project by id with result: " + (project == null ? " null" : project));
        }

        return project;
    }


    @Override
    public List<Project> getProjectByName(String project_name) {
        List<Project> list;

        if(project_name == null) list = null;
        else{
            list = pd.getProjectByName(project_name);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Project by name with size " + (list == null ? 0 : list.size()));
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public int removeProject(Project project) {
        int res = 0;

        if(project == null || project.getProject_id() == null) res = -1;
        else{
            if(project.getResouces() != null) project.setResouces(new HashSet<>());
            res = pd.removeProject(project);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("delete a Project with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(project or id) ]");
        }

        return res;
    }

    @Override
    public List<Project> paginationProject(Integer startIndex, Integer pageSize) {
        List<Project> list;

        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) list = null;
        else{
            list = pd.paginationProject(startIndex, pageSize);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Project by pagination with size " + (list == null ? 0 : list.size()));
        }

        return list;
    }
}
