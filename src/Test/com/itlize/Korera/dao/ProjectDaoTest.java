package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Project;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/1 - 4:36 PM
 */
public class ProjectDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
    ProjectDao pd = (ProjectDao) ac.getBean("ProjectDaoImpl");

    @Test
    public void addProjectTest() {
        Project project = new Project();
        for(int i = 1 ; i <= 100; i++) {
            project.setProject_id(i);
            project.setProject_name("hello" + i);
            //project.setUser_id(5);
            int res = pd.addProject(project);
            System.out.println("res: " + res);
        }
    }

    @Test
    public void updateProjectTest() {
        Project project = new Project();
        project.setProject_id(1);
        project.setProject_name("hello");
        //project.setUser_id(5);
        int res = pd.updateProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void saveOrUpdateProjectTest() {
        Project project = new Project();
        project.setProject_name("hello");
        //project.setUser_id(5);
        int res = pd.saveOrUpdateProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void listProjectsTest() {
        List<Project> list = pd.listProjects();
        System.out.println(list.toString());
    }

    @Test
    public void getProjectByIdTest() {
        Project project = pd.getProjectById(1);
        System.out.println(project.toString());
    }

    @Test
    public void getProjectByIdName() {
        List<Project> list = pd.getProjectByName("hello");
        System.out.println(list);
    }

    @Test
    public void removeProjectTest() {
        Project project = new Project();
        project.setProject_id(2);
        project.setProject_name("hello");
        //project.setUser_id(5);
        int res = pd.removeProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void paginationProjectTest() {
        int pageIndex = 5, pageSize = 10;
        List<Project> list = pd.paginationProject(pageIndex, pageSize);
        System.out.println(list);
    }

}
