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
        Project project = new Project("ptest02", new User(4));
        project.getResouces().add(new Resource(5));
        System.out.println(pd.addProject(project));
    }

    @Test
    public void updateProjectTest() {
        Project project = new Project(1, "ptest02", new User(1,"xiaoming", "1231"));
        project.getResouces().add(new Resource(1));
        int res = pd.updateProject(project);
        System.out.println("res: " + res);
    }

    @Test
    public void saveOrUpdateProjectTest() {
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
    public void getProjectTest() {
        Project project = pd.getProjectById(22);
        System.out.println(project.toString());
    }

    @Test
    public void getProjectByName() {
        List<Project> list = pd.getProjectByName("hello");
        System.out.println(list);
    }

    @Test
    public void removeProjectTest() {
        int res = pd.removeProject(pd.getProjectById(26));
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
