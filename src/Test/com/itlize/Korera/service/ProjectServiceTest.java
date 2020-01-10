package com.itlize.Korera.service;

import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.ProjectServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/2 - 10:37 AM
 */
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProjectServiceTest {

    @Autowired
    @Qualifier("ProjectServicesImpl")
    ProjectServices ps;


//    @Test
//    public void addProjectTest() {
//        Project project = new Project();
//        project.setProject_name("hello" + 101);
//        project.setUser_id(1);
//        int res = ps.addProject(project);
//        System.out.println(res);
//    }
//
    @Test
    public void updateProjectTest() {
        Project project = new Project();
        project.setProject_id(1);
        project.setProject_name("hello2222" + 101);
        project.setUser(new User(1, "", ""));
        int res = ps.updateProject(project);
        System.out.println("res: " + res);
    }
//
//    @Test
//    public void saveOrUpdateProjectTest() {
//        Project project = new Project();
//        project.setProject_id(4);
//        project.setProject_name("hello");
//        project.setUser_id(1);
//        int res = ps.saveOrUpdateProject(project);
//        System.out.println("res: " + res);
//    }
//
//
//    @Test
//    public void listProjectsTest() {
//        List<Project> list = ps.listProjects();
//        System.out.println(list.toString());
//    }
//
//    @Test
//    public void getProjectByIdTest() {
//        Project project = ps.getProjectById(1);
//        if(project != null) System.out.println(project.toString());
//        else System.out.println("null");
//    }
//
//
//    @Test
//    public void getProjectByIdName() {
//        List<Project> list = ps.getProjectByName("hello");
//        System.out.println(list);
//    }
//
//    @Test
//    public void removeProjectTest() {
//        Project project = new Project();
//        project.setProject_id(101);
//        project.setProject_name("hello");
//        project.setUser_id(5);
//        int res = ps.removeProject(project.getProject_id());
//        System.out.println("res: " + res);
//    }
    @Test
    public void getProjectTest() {
        Project project = ps.getProject(22);
        System.out.println(project.toString());
    }

    @Test
    public void paginationProjectTest() {
        int pageIndex = 5, pageSize = 10;
        List<Project> list = ps.paginationProject(pageIndex, pageSize);
        System.out.println(list);
    }


}
