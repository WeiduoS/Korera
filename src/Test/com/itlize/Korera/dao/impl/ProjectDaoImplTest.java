package com.itlize.Korera.dao.impl;

import com.itlize.Korera.dao.ProjectDao;
import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/22 - 3:41 PM
 */
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProjectDaoImplTest {

    @Autowired
    @Qualifier(value = "ProjectDaoImpl")
    ProjectDao pd;

    @Test
    public void addProject() {
        // test resource cascade
//        Project project = new Project("ptest02", new User(4));
//        project.getResouces().add(new Resource(42));
//        System.out.println(pd.addProject(project));
        // test user cascade
        Project project = new Project("user cascade", new User(4));
        project.setUser(new User(4));
        System.out.println(pd.addProject(project));

    }

    @Test
    public void updateProject() {
        Project project = new Project(1, "ptest02", new User(1,"xiaoming", "1231"));
        project.getResouces().add(new Resource(1));
        int res = pd.updateProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void saveOrUpdateProject() {
//        for(int i = 1; i <= 5; i++) {
//            Project p = new Project(i, "tests", new User(1,"xiaoming", "1231"));
//            System.out.println(pd.saveOrUpdateProject(p));
//        }
//        for(int i = 1; i <= 5; i++) {
//            Project p = new Project("xiaoming project", new User(1,"xiaoming", "1231"));
//            System.out.println(pd.saveOrUpdateProject(p));
//        }
        Project project = new Project(37,"p4new01", new User(4));
        project.getResouces().add(new Resource(5, "000", "000", new Category(2, "")));
        System.out.println(pd.saveOrUpdateProject(project));
    }

    @Test
    public void listProjects() {
        List<Project> list = pd.listProjects();
        System.out.println(list.toString());
    }

    @Test
    public void getProjectById() {
        Project project = pd.getProjectById(22);
        System.out.println(project.toString());
    }

    @Test
    public void getProjectByName() {
        List<Project> list = pd.getProjectByName("hello");
        System.out.println(list);
    }

    @Test
    public void removeProject() {
        int res = pd.removeProject(pd.getProjectById(41));
        System.out.println("res: " + res);
    }

    @Test
    public void getProjectSize() {
        BigInteger res = pd.getProjectSize();
        System.out.println(res);
    }

    @Test
    public void paginationProject() {
        int pageIndex = 1, pageSize = 1;
        List<Project> list = pd.paginationProject(pageIndex, pageSize);
        System.out.println(list);
    }
}