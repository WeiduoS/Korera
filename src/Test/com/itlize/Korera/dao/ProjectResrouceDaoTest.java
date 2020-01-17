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
public class ProjectResrouceDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    ProjectResourceDao mappingDao = (ProjectResourceDao) ac.getBean("ProjectResourceDaoImpl");

    @Test
    public void addMappingTest() {
        ProjectResource projectResource;
        for(int i = 1; i <= 5; i++) {
            projectResource = new ProjectResource(27, 5);
            for(int j = 1; j <= 3; j++) {
                projectResource.getCols().add(new Cols("field", "type","formula", "value", projectResource));
            }
            System.out.println(mappingDao.addMapping(projectResource));
        }
    }

    @Test
    public void listMappingsTest() {
        List<ProjectResource> list = mappingDao.listMappings();
        System.out.println(list);
    }

    @Test
    public void getMappingByIdTest() {
        System.out.println(mappingDao.getMappingById(27, 5));
    }

    @Test
    public void saveOrUpdateMappingTest() {
        List<ProjectResource> list = mappingDao.getMappingById(28, 5);
        for(ProjectResource pr : list) {
            for(int i = 1; i <= 10; i++) {
                pr.getCols().add(new Cols("1", "1", "1", "1", pr));
            }
            System.out.println(mappingDao.saveOrUpdateMapping(pr));
        }
    }

    @Test
    public void removeMappingTest() {
        List<ProjectResource> list = mappingDao.getMappingById(27, 5);
        for(ProjectResource pr : list) {
            System.out.println(mappingDao.removeMapping(pr));
        }
    }
}
