package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Proj_res_mapping;
import com.itlize.Korera.entities.Project;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:48 PM
 */
public interface Proj_res_mappingDao {

    public int addMapping(Proj_res_mapping mapping);

    public int updateMapping(Proj_res_mapping mapping);

    public List<Proj_res_mapping> listMappings();

    public Proj_res_mapping getMappingById(Integer project_id, Integer resource_id);

    public int removeMapping(Proj_res_mapping mapping);



}
