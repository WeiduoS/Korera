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
public class ColsDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    ColsDao colsDao = (ColsDao) ac.getBean("ColsDaoImpl");

    @Test
    public void addColsMappingTest() {
    }

    @Test
    public void listColsTest() {
    }

    @Test
    public void getColsByIdTest() {
        int project_id = 22, resource_id = 35;
        List<Cols> cols = colsDao.getColsById(project_id, resource_id);
        System.out.println(cols);
    }

    @Test
    public void removeMappingTest() {
    }
}
