package com.itlize.Korera.controller;

import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = {"/findall"},method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> findAll(Model model){
        if(resourceService == null) return new ArrayList<>();
        List<Resource> resources = resourceService.listResources();
        return resources;
    }

    @RequestMapping(value = "/viewlist",method = RequestMethod.GET)
    public String listResources(Model model){
        if (resourceService == null) return "";
        List<Resource> resources = resourceService.listResources();
        model.addAttribute("resources",resources);
        return "resources/resources";
    }

    @RequestMapping(value = "/findByID/{resourceId}",method = RequestMethod.GET)
    @ResponseBody
    public  Resource findByID(@PathVariable("resourceId") Integer id){
        if (resourceService == null) return null;
        Resource r = resourceService.getResourceById(id);
        return r;
    }

    @RequestMapping(value = "/findByName/{resourceName}",method = RequestMethod.GET)
    @ResponseBody
    public  List<Resource> findByName(@PathVariable("resourceName") String name){
        if(resourceService == null) return new ArrayList<>();
        List<Resource> rl = resourceService.getResourceByName(name);
        return rl;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addform(Model model){
        ModelAndView mv = new ModelAndView("resources/add","r_add",new Resource());
//        mv.addObject("r_add",new Resource());
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addResource(@ModelAttribute("r_add")  Resource resource){
        System.out.println(resource.toString());
        resourceService.addResource(resource);
        return "redirect:/resource/viewlist";
    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @ResponseBody
//    public Resource addResource(@ModelAttribute("r_add")  Resource resource){
//        System.out.println(resource.toString());
//        resourceService.addResource(resource);
//        return resource;
//    }

    @RequestMapping(value = "/delete/{resource_id}", method = RequestMethod.DELETE)
    public String deleteResource(@PathVariable("resource_id") Integer id){
        Resource r = resourceService.getResourceById(id);
        resourceService.removeResource(r);
        return "redirect:/resource/viewlist";

    }

    @RequestMapping(value = "/update/{resource_id}", method = RequestMethod.GET)
    public ModelAndView editResource(@PathVariable("resource_id") Integer id){
        ModelAndView mv = new ModelAndView("resources/edit");
        Resource r = resourceService.getResourceById(id);
        mv.addObject("r_edit",r);
        return mv;
    }

//    @RequestMapping(value = "/update/{resource_id}", method = RequestMethod.PUT)
//    public String updateResource(@PathVariable("resource_id") Integer id, @ModelAttribute("r_edit") Resource resource){
//        resource.setResourceId(id);
//        resourceService.updateResource(resource);
//
//        return "redirect:/resource/viewlist";
//    }

    @RequestMapping(value = "/update/{resource_id}", method = RequestMethod.PUT)
    @ResponseBody
    public Resource updateResource(@PathVariable("resource_id") Integer id, @ModelAttribute("r_edit") Resource resource){
        resource.setResourceId(id);
        resourceService.updateResource(resource);
        resource = resourceService.getResourceById(id);
        return resource;
    }

//    @RequestMapping(value = "/update/{resource_id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public Resource updatejson(@PathVariable("resource_id") Integer id, @ModelAttribute("r_edit") Resource resource){
//        resource.setResourceId(id);
//        System.out.println("+++++++++-------"+resource.toString());
//        resourceService.updateResource(resource);
//        resource = resourceService.getResourceById(id);
//        return resource;
//
//    }




}
