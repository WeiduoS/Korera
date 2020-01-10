package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.PRMPKey;
import com.itlize.Korera.entities.Proj_res_mapping;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:53 PM
 */
public class Proj_res_mappingDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    Proj_res_mappingDao mappingDao = (Proj_res_mappingDao) ac.getBean("ProjectResourceDaoImpl");

    @Test
    public void addMappingTest() {
        Proj_res_mapping mapping = new Proj_res_mapping(new PRMPKey(76, 72));
        Cols col = new Cols(mapping, "field", "type", "formula", "value");
        mapping.getCols().add(col);
        int res = mappingDao.addMapping(mapping);
        System.out.println(res);
    }

    @Test
    public void listMappingsTest() {
        List<Proj_res_mapping> list = mappingDao.listMappings();
        System.out.println(list);
    }

    @Test
    public void getMappingByIdTest() {
        int project_id = 22, resource_id = 35;
        Proj_res_mapping mapping = mappingDao.getMappingById(project_id, resource_id);
        System.out.println(mapping.toString());
    }

    @Test
    public void removeMappingTest() {
        Proj_res_mapping mapping = new Proj_res_mapping(new PRMPKey(22, 35));
        mapping.getCols().add(new Cols(10, mapping));
        System.out.println(mappingDao.removeMapping(mapping));
    }
}
