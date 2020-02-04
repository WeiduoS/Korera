package com.itlize.Korera.service.impl;

import com.itlize.Korera.config.SpringConfig;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.service.ProjectServices;
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
 * @date 2020/1/30 - 2:53 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@Transactional
public class ProjectServicesImplTest {

    @Autowired
    @Qualifier(value = "ProjectServicesImpl")
    ProjectServices projectServices;
    @Test
    public void addProject() {
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void saveOrUpdateProject() {
    }

    @Test
    public void listProjects() {
    }

    @Test
    public void getProjectById() {
    }

    @Test
    public void getProjectByName() {
    }

    @Test
    public void removeProject() {
        System.out.println(projectServices.removeProject(new Project(23)));
    }

    @Test
    public void paginationProject() {
    }
}