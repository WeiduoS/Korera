package com.itlize.Korera.controller;

import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.service.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    @Qualifier(value = "ResourceServicesImpl")
    private ResourceServices resourceService;

    @RequestMapping(value = {"/findAll"},method = RequestMethod.GET)
    public List<Resource> findAll(Model model){
        if(resourceService == null) return new ArrayList<>();
        List<Resource> resources = resourceService.listResources();
        return resources;
    }

    @RequestMapping(value = "/pagination/{page}",method = RequestMethod.GET)
    public ResponseEntity<List<Resource>> pagination(@PathVariable("page") String page){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        if(resourceService == null) return new ResponseEntity<>(new ArrayList<>(), headers, status);

        String[] strs = page.split("-");
        List<Resource> resources = new ArrayList<>();

        try{
            Integer start = Integer.valueOf(strs[0]);
            Integer size = Integer.valueOf(strs[1]);
            status = HttpStatus.OK;
            resources = resourceService.paginationResource(start, size);
        }catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;

        }

        ResponseEntity<List<Resource>> responseEntity = new ResponseEntity<>(resources,
                headers,
                status);
        return responseEntity;
    }

    @RequestMapping(value = "/findById/{resource_id}",method = RequestMethod.GET)
    public Resource findByID(@PathVariable("resource_id") Integer id){
        if (resourceService == null) return null;
        Resource r = resourceService.getResourceById(id);
        return r;
    }

    @RequestMapping(value = "/findByName/{resource_name}",method = RequestMethod.GET)
    public  List<Resource> findByName(@PathVariable("resource_name") String name){
        if(resourceService == null) return new ArrayList<>();
        List<Resource> rl = resourceService.getResourceByName(name);
        return rl;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Resource> addResource(RequestEntity<Resource> requestEntity){
        Resource resource = requestEntity.getBody();

        HttpStatus status = null;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = -1;
        res = resourceService.addResource(resource);

        if(res > 0) status = HttpStatus.CREATED;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Resource> responseEntity = new ResponseEntity<Resource>(resource,
                headers,
                status);
        return responseEntity;

    }

    @RequestMapping(value = "/delete/{resource_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource> deleteResource(@ModelAttribute("resource") Resource resource){
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = resourceService.removeResource(resource);

        if(res > 0) status = HttpStatus.OK;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Resource> responseEntity = new ResponseEntity<>(resource,
                headers,
                status);
        return responseEntity;
    }


    @RequestMapping(value = "/update/{resource_id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource> updateResource(@ModelAttribute("resource") Resource resource, RequestEntity<Resource> requestEntity){
        resource = updateResourceMapping(resource, requestEntity.getBody());

        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = resourceService.saveOrUpdateResource(resource);

        if(res > 0) status = HttpStatus.OK;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Resource> responseEntity = new ResponseEntity<>(resource,
                headers,
                status);
        return responseEntity;
    }


    @ModelAttribute(value = "/*/{resource_id}")
    public void preResourceRequest(@PathVariable(value = "resource_id", required = false) Integer resource_id, Model model) {
        if(resource_id != null) {
            Resource resource = resourceService.getResourceById(resource_id);
            if(resource == null) model.addAttribute("resource", new Resource());
            else model.addAttribute("resource", resource);
        }
    }

    private Resource updateResourceMapping(Resource db_resource, Resource web_resource) {
        if(web_resource.getResource_name() != null && !web_resource.getResource_name().equals("")) db_resource.setResource_name(web_resource.getResource_name());
        if(web_resource.getResource_code() != null && !web_resource.getResource_code().equals("")) db_resource.setResource_code(web_resource.getResource_code());
        if(!web_resource.getCols().isEmpty()) db_resource.getCols().addAll(web_resource.getCols());
        return db_resource;
    }

}
