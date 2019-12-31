package com.itlize.Korera.controller;

import com.itlize.Korera.entities.Project;
import com.itlize.Korera.service.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 4:02 PM
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectServices projectServices;

    @RequestMapping("/findAll")
    public String findAll() {
        System.out.println("represent layer: list all project");
        List<Project> res = projectServices.findAll();
        System.out.println(res);
        return "success";
    }
}
