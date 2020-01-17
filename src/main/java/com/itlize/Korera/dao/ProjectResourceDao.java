package com.itlize.Korera.dao;

import com.itlize.Korera.entities.ProjectResource;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:48 PM
 */
public interface ProjectResourceDao {

    public int addMapping(ProjectResource mapping);

    public int updateMapping(ProjectResource mapping);

    public int saveOrUpdateMapping(ProjectResource mapping);

    public List<ProjectResource> listMappings();

    public List<ProjectResource> getMappingById(Integer project_id, Integer resource_id);

    public int removeMapping(ProjectResource mapping);

}
