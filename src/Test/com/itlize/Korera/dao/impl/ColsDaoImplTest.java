package com.itlize.Korera.dao.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.dao.ColsDao;
import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.ProjResId;
import com.itlize.Korera.entities.ProjectResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/28 - 5:58 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@Transactional
public class ColsDaoImplTest {

    @Autowired
    @Qualifier(value = "ColsDaoImpl")
    ColsDao colsDao;

    @Autowired
    @Qualifier(value = "ProjectResourceDaoImpl")
    ProjectResourceDao prd;

    @Test
    public void addCols() {
        ProjectResource projectResource = prd.getMappingById(1, 1).get(0);
        for(int i = 1; i <= 5; i++) {
//            Cols cols = new Cols("1", "1", "1", "1",1, 1);
            Cols cols = new Cols("1", "1", "1", "1");
            cols.setProjectResource(projectResource);
            System.out.println(colsDao.addCols(cols));
        }
    }

    @Test
    public void updateCols() {
    }

    @Test
    public void saveOrUpdateCols() {
    }

    @Test
    public void getColsById() {
    }

    @Test
    public void listCols() {
    }

    @Test
    public void removeCols() {
    }
}