package com.itlize.Korera.service.impl;

import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.dao.ResourceDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.ProjectResource;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.service.ResourceServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
@Service("ResourceServicesImpl")
public class ResourceServicesImpl implements ResourceServices {
    @Autowired
    ResourceDao rd;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int addResource(Resource resource) {
        int res = 0;
        if(resource == null) res = -1;
        else {
            res = rd.addResource(resource);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("adding a Resource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(resource)]");
        }

        return res;
    }

    @Override
    public int updateResource(Resource resource) {
        int res = 0;
        if(resource == null || resource.getResource_id() == null) res = -1;
        else {
            res = rd.updateResource(resource);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("updating a Resource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(resource or id)]");
        }

        return res;
    }

    @Override
    public int saveOrUpdateResource(Resource resource) {
        int res = 0;
        if(resource == null) res = -1;
        else {
            res = rd.saveOrUpdateResource(resource);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("saveOrUpdate a Resource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(resource)]");
        }

        return res;
    }

    @Override
    public List<Resource> listResources() {
        List<Resource> list = rd.listResources();

        int res = list.size();

        if(logger.isDebugEnabled()) {
            logger.debug("list all Resource with size " + res);
        }

        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public Resource getResourceById(Integer id) {
        Resource resource;
        if(id == null) return null;
        else{
            resource = rd.getResourceById(id);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Resource by id with result: " + (resource == null ? " null" : resource));
        }

        return resource;
    }

    @Override
    public List<Resource> getResourceByName(String resource_name) {
        List<Resource> list;
        if(resource_name == null) list = null;
        else{
            list = rd.getResourceByName(resource_name);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Resource by name with size " + (list == null ? 0 : list.size()));
        }

        return list == null ? new ArrayList<>() : list;

    }

    @Override
    public int removeResource(Resource resource) {
        int res = 0;

        if(resource == null || resource.getResource_id() == null) res = -1;
        else{
            res = rd.removeResource(resource);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("delete a Resource with result " + res + ", [ 1 for success, 0 for unknown error, -1 for null pointer(resource or id) ]");
        }

        return res;
    }

    @Override
    public BigInteger getResourceSize() {
        BigInteger res = rd.getResourceSize();

        if(logger.isDebugEnabled()) {
            logger.debug("get Resource size with result " + res);
        }

        return res;
    }

    @Override
    public List<Resource> paginationResource(Integer startIndex, Integer pageSize) {

        List<Resource> list;
        if(startIndex == null || pageSize == null || startIndex <= 0 || pageSize <= 0) list = null;
        else{
            list = rd.paginationResource(startIndex, pageSize);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("find Resource by pagination with size(index: + " + startIndex + ", page size: "+ pageSize + ") " + (list == null ? 0 : list.size()));
        }

        return list;
    }
}
