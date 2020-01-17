package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.entities.ProjectResource;
import com.itlize.Korera.service.ProjectResourceServices;
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

    @Override
    public int addMapping(ProjectResource mapping) {
        if(mapping == null) return -1;
        return prd.addMapping(mapping);
    }

    @Override
    public int updateMapping(ProjectResource mapping) {
        if(mapping == null) return -1;
        return prd.updateMapping(mapping);
    }

    @Override
    public int saveOrUpdateMapping(ProjectResource mapping) {
        if(mapping == null) return -1;
        return prd.saveOrUpdateMapping(mapping);
    }

    @Override
    public List<ProjectResource> listMappings() {
        return prd.listMappings();
    }

    @Override
    public List<ProjectResource> getMappingById(Integer project_id, Integer resource_id) {
        if(project_id == null || resource_id == null || project_id < 0 || resource_id < 0) return new ArrayList<>();
        return prd.getMappingById(project_id, resource_id);
    }

    @Override
    public int removeMapping(ProjectResource mapping) {
        if(mapping == null) return -1;
        return prd.removeMapping(mapping);
    }
}
