package com.itlize.Korera.dao.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.dao.ProjectResourceDao;
import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.ProjResId;
import com.itlize.Korera.entities.ProjectResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/28 - 10:11 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@Transactional
public class ProjectResourceDaoImplTest {

    @Autowired
    ProjectResourceDao mappingDao;

    @Test
    public void addMapping() {
        ProjectResource projectResource = new ProjectResource(new ProjResId(1, 1));
//        for(int i = 1; i <= 10; i++) {
//            projectResource.getCols().add(new Cols("1" + i, "1" + i , "1" + i, "1" + i, projectResource));
//        }
        System.out.println(mappingDao.addMapping(projectResource));
    }

    @Test
    public void updateMapping() {
    }

    @Test
    public void saveOrUpdateMapping() {
        List<ProjectResource> list = mappingDao.getMappingById(1, 1);
        System.out.println(list);
        for(ProjectResource pr : list) {
            for(int i = 1; i <= 10; i++) {
                Cols cols = new Cols("1", "1", "1", "1");
                cols.setProjectResource(pr);
                pr.getCols().add(cols);
            }
            System.out.println(mappingDao.saveOrUpdateMapping(pr));
        }
    }

    @Test
    public void listMappings() {
        List<ProjectResource> list = mappingDao.listMappings();
        System.out.println(list);
    }

    @Test
    public void getMappingById() {
        System.out.println(mappingDao.getMappingById(1, 1));
    }

    @Test
    public void removeMapping() {
    }
}