package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.ProjectResource;
import com.itlize.Korera.service.ProjectResourceServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/11 - 5:08 PM
 */
@Service(value = "ProjectResourceServicesImpl")
public class ProjectResourceServicesImpl implements ProjectResourceServices {

    @Autowired
    @Qualifier("ProjectResourceDaoImpl")
    ProjectResourceDao prd;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int addMapping(ProjectResource mapping) {
        int res = 0;

        if(mapping == null) res = -1;
        else {
            res = prd.addMapping(mapping);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("adding a ProjectResource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(mapping) ]");
        }

        return res;
    }

    @Override
    public int updateMapping(ProjectResource mapping) {
        int res = 0;

        if(mapping == null
                || mapping.getProjResId() == null
                || mapping.getProjResId().getProject_id() == null
                || mapping.getProjResId().getResource_id() == null) res = -1;
        else {
            List<ProjectResource> mappingById = prd.getMappingById(mapping.getProjResId().getProject_id(), mapping.getProjResId().getResource_id());
            if(mappingById == null || mappingById.isEmpty()) res = -2;
            else{
                res = prd.updateMapping(mapping);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("updating a ProjectResource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer, -2 for no such ProjectResource(mapping or project id or resource id ). ]");
        }

        return res;
    }

    @Override
    public int saveOrUpdateMapping(ProjectResource mapping) {
        int res = 0;

        if(mapping == null) res = -1;
        else{
            res = prd.saveOrUpdateMapping(mapping);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("saveOrUpdate a ProjectResource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(mapping). ]");
        }

        return res;
    }

    @Override
    public List<ProjectResource> listMappings() {
        List<ProjectResource> list = prd.listMappings();

        int res = list.size();

        if(logger.isDebugEnabled()) {
            logger.debug("list all ProjectResource with size " + res);
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public List<ProjectResource> getMappingById(Integer project_id, Integer resource_id) {
        List<ProjectResource> mappings;

        if(project_id == null || resource_id == null) return mappings = new ArrayList<>();
        else{
            mappings = prd.getMappingById(project_id, resource_id);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find ProjectResources by id with result: " + (mappings == null ? " null" : mappings));
        }

        return mappings;
    }

    @Override
    public int removeMapping(ProjectResource mapping) {
        int res = 0;

        if(mapping == null
                || mapping.getProjResId() == null
                || mapping.getProjResId().getProject_id() == null
                || mapping.getProjResId().getResource_id() == null) res = -1;
        else{
            res = prd.removeMapping(prd.getMappingById(mapping.getProjResId().getProject_id(), mapping.getProjResId().getResource_id()).get(0));
        }

        if(logger.isDebugEnabled()) {
            logger.debug("delete a ProjectResource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(mapping or project id or resource id) ]");
        }

        return res;
    }
}
