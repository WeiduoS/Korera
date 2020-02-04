package com.itlize.Korera.service.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.ProjResId;
import com.itlize.Korera.entities.ProjectResource;
import com.itlize.Korera.service.ProjectResourceServices;
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
 * @date 2020/1/30 - 11:09 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@Transactional
public class ProjectResourceServicesImplTest {

    @Autowired
    @Qualifier(value = "ProjectResourceServicesImpl")
    ProjectResourceServices projectResourceServices;

    @Test
    public void addMapping() {
    }

    @Test
    public void updateMapping() {
    }

    @Test
    public void saveOrUpdateMapping() {
        ProjectResource projectResource = new ProjectResource(new ProjResId(32, 1));
        Cols cols01 = new Cols(16, "field02", "type02", "formula01", "value01", projectResource);
        Cols cols02 = new Cols("field01", "type01", "formula01", "value01", projectResource);
        Cols cols03 = new Cols("field01", "type01", "formula01", "value01", projectResource);
        projectResource.getCols().add(cols01);
        projectResource.getCols().add(cols02);
        projectResource.getCols().add(cols03);

        System.out.println(projectResourceServices.saveOrUpdateMapping(projectResource));
    }

    @Test
    public void listMappings() {
    }

    @Test
    public void getMappingById() {
    }

    @Test
    public void removeMapping() {
    }
}