package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.ProjectResource;
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
    public void addColsTest() {
        for(int i = 1; i <= 5; i++) {
            Cols cols = new Cols("1", "1", "1", "1", new ProjectResource(6, 16,5));
            System.out.println(colsDao.addCols(cols));
        }
    }

    @Test
    public void listColsTest() {
        List<Cols> cols = colsDao.listCols();
        System.out.println(cols);
    }

    @Test
    public void getColsByIdTest() {
        Cols cols = colsDao.getColsById(16);
        System.out.println(cols);
    }

    @Test
    public void updateColsTest() {

    }

    @Test
    public void saveOrupdateColsTest() {
        for(int i = 1; i <= 5; i++) {
            Cols cols = new Cols("1", "1", "1", "1", new ProjectResource(6, 16,5));
            System.out.println(colsDao.addCols(cols));
        }
    }

    @Test
    public void removeColsTest() {
        Cols cols = new Cols(16);
        System.out.println(colsDao.removeCols(cols));
    }
}
