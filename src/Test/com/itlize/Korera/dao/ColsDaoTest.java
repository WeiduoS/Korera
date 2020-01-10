package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.PRMPKey;
import com.itlize.Korera.entities.Proj_res_mapping;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.Transient;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:53 PM
 */
public class ColsDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    ColsDao colsDao = (ColsDao) ac.getBean("ColsDaoImpl");

    @Test
    public void addColsTest() {
        Cols cols = new Cols(new Proj_res_mapping(new PRMPKey(76, 72)), "field", "type", "formula", "value");
        System.out.println(colsDao.addCols(cols));
    }

    @Test
    public void listColsTest() {
        List<Cols> cols = colsDao.listCols();
        System.out.println(cols);
    }

    @Test
    public void getColsByIdTest() {
        int project_id = 22, resource_id = 35;
        List<Cols> cols = colsDao.getColsById(project_id, resource_id);
        System.out.println(cols);
    }

    @Test
    public void updateColsTest() {
        Cols cols = new Cols(17, new Proj_res_mapping(new PRMPKey(76, 72)), "field1", "type1", "formula1", "value1");
        System.out.println(colsDao.updateCols(cols));
    }

    @Test
    public void removeColsTest() {
        Cols cols = new Cols(16);
        System.out.println(colsDao.removeCols(cols));
    }
}
