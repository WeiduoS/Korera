package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Resource;

import java.math.BigInteger;
import java.util.List;

public interface ResourceDao {
    public int addResource(Resource resource);

    public int updateResource(Resource resource);

    public int saveOrUpdateResource(Resource resource);

    public List<Resource> listResources();

    public Resource getResourceById(Integer id);

    public List<Resource> getResourceByName(String resource_name);

    public BigInteger getResourceSize();

    public int removeResource(Resource resource);

    public List<Resource> paginationResource(Integer startIndex, Integer pageSize);
}
