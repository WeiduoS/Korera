package com.itlize.Korera.controller;

import com.itlize.Korera.entities.Project;
import com.itlize.Korera.service.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Weiduo
 * @date 2019/12/30 - 4:02 PM
 */
//@SessionAttributes(value = "msg", types = {String.class})
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired(required=false)
    @Qualifier("ProjectServicesImpl")
    private ProjectServices projectServices;

    // value, method, param, headers
    // consumes, produces
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll(Model model) {
        if(projectServices == null) return "";
        List<Project> projects = projectServices.listProjects();
        model.addAttribute("projects", projects);
        return "projects/projects";
}

    @RequestMapping(value = "/findById/{project_id}", method = RequestMethod.GET)
    public String findByID(@PathVariable("project_id")Integer project_id, Model model) {
         if(projectServices == null || project_id == null) return "";
        System.out.println("project find by id: " + project_id);
        Project project = projectServices.getProjectById(project_id);
        model.addAttribute("project", project);
        System.out.println(project.toString());
        return "projects/editProject";
    }

    @RequestMapping(value = "/findByName/{project_name}", method = RequestMethod.GET)
    public String findByName (@PathVariable("project_name")String project_name) {
        if(projectServices == null) return "";
        System.out.println("project find by name: " + project_name);
        return "success";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView preAddProject(Model model) {
        ModelAndView mv = new ModelAndView("/projects/addProject");
        mv.addObject("command", new Project());
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST )
    public String addProject(Project project){
        System.out.println(project.toString());
        projectServices.addProject(project);
        return "redirect:/project/findAll";
    }

    @RequestMapping(value = "/update/{project_id}", method = RequestMethod.PUT)
    public String updateProject(@PathVariable("project_id")String project_id, @ModelAttribute("project") Project project){
        System.out.println("update a project: " + project.toString());
        projectServices.saveOrUpdateProject(project);
        return "redirect:/project/findAll";
    }

    @ModelAttribute
    public void preUpdateProject(@RequestParam(value = "project_id", required = false) Integer project_id, Model model) {
        if(project_id != null) {
            Project project = projectServices.getProjectById(project_id);
            model.addAttribute("project", project);
            System.out.println("mode attribute project: " + project.toString());
        }
    }

    @RequestMapping(value = "/delete/{project_id}", method = RequestMethod.DELETE)
    public String deleteProject(@PathVariable("project_id")Integer project_id){
        System.out.println("delete a project: " + project_id);
        projectServices.removeProject(project_id);
        return "redirect:/project/findAll";
    }

}
