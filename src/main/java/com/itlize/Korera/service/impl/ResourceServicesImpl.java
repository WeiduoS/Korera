package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ResourceDao;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.service.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
@Service("ResourceServicesImpl")
public class ResourceServicesImpl implements ResourceServices {
    @Autowired
    ResourceDao rd;

    @Override
    public int addResource(Resource resource) {
        if(resource == null) return -1;
        int res = rd.addResource(resource);
        return res;
    }

    @Override
    public int updateResource(Resource resource) {
        if(resource == null) return -1;
        int res = rd.updateResource(resource);
        return res;
    }

    @Override
    public int saveOrUpdateResource(Resource resource) {
        if(resource == null) return -1;
        int res = rd.saveOrUpdateResource(resource);
        return res;
    }

    @Override
    public List<Resource> listResources() {
        List<Resource> res = rd.listResources();
        return res == null ? new ArrayList<>() : res;
    }

    @Override
    public Resource getResourceById(Integer id) {
        if(id == null || id < 0) return null;
        Resource res = rd.getResourceById(id);
        return res;
    }

    @Override
    public List<Resource> getResourceByName(String resource_name) {
        if(resource_name == null) return null;
        List<Resource> res = rd.getResourceByName(resource_name);
        return res == null ? new ArrayList<>() : res;

    }

    @Override
    public int removeResource(Resource resource) {
        if(resource == null) return -1;
        int res = rd.removeResource(resource);
        return res;
    }

    @Override
    public BigInteger getResourceSize() {
        return rd.getResourceSize();
    }

    @Override
    public List<Resource> paginationResource(Integer startIndex, Integer pageSize) {
        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) return new ArrayList<>();
        List<Resource> res = rd.paginationResource(startIndex, pageSize);
        return res == null ? new ArrayList<>() : res;
    }
}
