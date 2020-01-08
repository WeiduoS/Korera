package com.itlize.Korera.service;

import com.itlize.Korera.entities.Resource;

import java.util.List;

public interface ResourceService {

    public int addResource(Resource resource);

    public int updateResource(Resource resource);

    public int saveOrUpdateResource(Resource resource);

    public List<Resource> listResources();

    public Resource getResourceById(Integer id);

    public List<Resource> getResourceByName(String resource_name);

    public int removeResource(Resource resource);

    public List<Resource> paginationResource(Integer startIndex, Integer pageSize);

}