package com.itlize.Korera.controller;

import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.ProjectResource;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author Weiduo
 * @date 2019/12/30 - 4:02 PM
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired(required=false)
    @Qualifier("ProjectServicesImpl")
    private ProjectServices projectServices;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> findAll() {
        if(projectServices == null) return new ArrayList<>();
        List<Project> projects = projectServices.listProjects();
        return projects;
    }

    @RequestMapping(value = "/findById/{project_id}", method = RequestMethod.GET)
    @ResponseBody
    public Project findByID(@PathVariable("project_id")Integer project_id, Model model) {
        if(projectServices == null || project_id == null) return new Project();
//        Project project = projectServices.getProjectById(project_id);
        return model.getAttribute("project") == null ? new Project() : (Project) model.getAttribute("project");
    }


    @RequestMapping(value = "/findByName/{project_name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> findByName (@PathVariable("project_name")String project_name) {
        if(projectServices == null) return new ArrayList<>();
        List<Project> projects = projectServices.getProjectByName(project_name);
        return projects;
    }

//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public ModelAndView preAddProject(Model model) {
//        ModelAndView mv = new ModelAndView("/projects/addProject");
//        mv.addObject("command", new Project());
//        return mv;
//    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addProject(Project project, HttpServletRequest request){
//        Integer userId = Integer.valueOf(request.getParameter("user_id"));
//        System.out.println("project: " + project);
//        projectServices.addProject(project);
//        return "redirect:/project/findAll";
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addProject(RequestEntity<Project> requestEntity){

        Project project = requestEntity.getBody();

        System.out.println("request body: " + requestEntity.getBody());
        System.out.println("project : " + project);
        System.out.println("user : " + project.getUser());
        System.out.println("request headers: " + requestEntity.getHeaders());
        System.out.println("request method : " + requestEntity.getMethod());


        HttpStatus status = null;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = projectServices.addProject(project);
        String body = "";

        if(res > 0) {
            status = HttpStatus.OK;
            body = "add successfully";
        }
        else {
            status = HttpStatus.BAD_REQUEST;
            body = "wrong input";
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<>(body,
                headers,
                status);
        return responseEntity;
    }

    @RequestMapping(value = "/update/{project_id}", method = RequestMethod.PUT)
    public ResponseEntity updateProject(@ModelAttribute("project") @RequestBody Project project, RequestEntity<Project> requestEntity){

        project = updateProjectMapping(project, requestEntity.getBody());
        System.out.println("project: " + project);
        System.out.println("user: " + project.getUser());
        System.out.println("resource: " + project.getResouces());
        System.out.println("request body: " + requestEntity.getBody());
        System.out.println("request headers: " + requestEntity.getHeaders());
        System.out.println("request method : " + requestEntity.getMethod());

        HttpStatus status = null;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("text/plain;charset=UTF-8"));
        String body = "";

        int res =  projectServices.saveOrUpdateProject(project);

        if(res > 0) {
            status = HttpStatus.OK;
            body = "add successfully";
        }
        else {
            status = HttpStatus.BAD_REQUEST;
            body = "wrong input";
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<>(body,
                headers,
                status);
        return responseEntity;
    }

    @ModelAttribute(value = "/*/{project_id}")
    public void preProjectRequest(@PathVariable(value = "project_id", required = false) Integer project_id, Model model) {
        if(project_id != null) {
            Project project = projectServices.getProjectById(project_id);
            if(project == null)model.addAttribute("project", new Project());
            else model.addAttribute("project", project);
            System.out.println("mode attribute project: " + project.toString());
        }
    }

    private Project updateProjectMapping(Project db_project, Project web_project) {
        if(web_project.getProject_name() != null && !db_project.equals(web_project.getProject_name())) {
            db_project.setProject_name(web_project.getProject_name());
        }
        db_project.getResouces().addAll(web_project.getResouces());
        return db_project;
    }

    @RequestMapping(value = "/delete/{project_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProject(@PathVariable("project_id")Integer project_id, @ModelAttribute("project") Project project){

        System.out.println("delete project:" + project);


        HttpStatus status = null;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("text/plain;charset=UTF-8"));
        String body = "";

        int res =  projectServices.removeProject(project);

        if(res > 0) {
            status = HttpStatus.OK;
            body = "add successfully";
        }
        else {
            status = HttpStatus.BAD_REQUEST;
            body = "wrong input";
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<>(body,
                headers,
                status);

        return responseEntity;
    }

}
