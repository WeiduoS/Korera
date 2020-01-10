package com.itlize.Korera.dao;

import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.entities.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
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
//        Project project = new Project("ptest", new User("xiaoming", "1231"));
//        System.out.println(pd.addProject(project));
//        Project project = new Project("ptest", new User("xiaoming", "1231"));
        Project project = new Project("ptest", new User(1, "dsf", "12313"));
        project.getResouces().add(new Resource("001", "res1", new Category("hhh")));
        int res = pd.addProject(project);
        System.out.println(res);
    }

    @Test
    public void updateProjectTest() {
        Project project = new Project(56, "ptest02", new User(14,"xiaoming", "1231"));
        int res = pd.updateProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void saveOrUpdateProjectTest() {
        Project project = new Project(56, "ptest04", new User(14,"xiaoming", "1231"));
        int res = pd.updateProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void listProjectsTest() {
        List<Project> list = pd.listProjects();
        System.out.println(list.toString());
    }

    @Test
    public void getProjectByIdTest() {
        Project project = pd.getProjectById(13);
        System.out.println(project.toString());
    }

    @Test
    public void getProjectTest() {
        Project project = pd.getProjectById(22);
        System.out.println(project.toString());
    }

    @Test
    public void getProjectByIdName() {
        List<Project> list = pd.getProjectByName("hello");
        System.out.println(list);
    }

    @Test
    public void removeProjectTest() {
        Project project = new Project(22, "qqq", new User());
        project.getResouces().add(new Resource(35));
        int res = pd.removeProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void getProjectSizeTest(){
        BigInteger res = pd.getProjectSize();
        System.out.println(res);
    }

    @Test
    public void paginationProjectTest() {
        int pageIndex = 1, pageSize = 1;
        List<Project> list = pd.paginationProject(pageIndex, pageSize);
        System.out.println(list);
    }

}
