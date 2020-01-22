package com.itlize.Korera.controller;

import com.itlize.Korera.entities.Category;
import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.CategoryServices;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 4:02 PM
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired(required=false)
    @Qualifier("CategoryServicesImpl")
    private CategoryServices categoryServices;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> findAll() {
        if(categoryServices == null) return new ArrayList<>();
        List<Category> categories = categoryServices.listCategories();
        return categories;
    }

    @RequestMapping(value = "/findById/{category_id}", method = RequestMethod.GET)
    @ResponseBody
    public Category findByID(@PathVariable("category_id")Integer category_id, Model model) {
        if(categoryServices == null || category_id == null) return new Category();
        return (Category) model.getAttribute("category");
    }

    @RequestMapping(value = "/findByName/{category_name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> findByName (@PathVariable("category_name")String category_name) {
        if(categoryServices == null) return new ArrayList<>();
        System.out.println("category find by name: " + category_name);
        List<Category> categories = categoryServices.getCategoryByName(category_name);
        return categories;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addCategory(RequestEntity<Category> requestEntity){
        Category category = requestEntity.getBody();

        HttpStatus status = null;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("text/plain;charset=UTF-8"));
        String body = "";
        int res = -1;

        if(category != null) {
            for(Resource resource : category.getResources()) {
                resource.setCategory_id(category);
            }
            res = categoryServices.addCategory(category);
        }

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


    @RequestMapping(value = "/update/{category_id}", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@ModelAttribute("category") Category category, RequestEntity<Category> requestEntity){
        category = updateCategoryMapping(category, requestEntity.getBody());

        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("text/plain;charset=UTF-8"));
        String body = "";

        int res = categoryServices.saveOrUpdateCategory(category);

        if(res > 0) {
            status = HttpStatus.OK;
            body = "update successfully";
        }
        else if(res == -1){
            status = HttpStatus.NOT_ACCEPTABLE;
            body = "the category information is not acceptable";
        }else if(res == -2) {
            status = HttpStatus.NOT_ACCEPTABLE;
            body = "the category name is already exist";
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<>(body,
                headers,
                status);
        return responseEntity;
    }

    @ModelAttribute(value = "/*/{category_id}")
    public void preCategoryRequest(@PathVariable(value = "category_id", required = false) Integer category_id, Model model) {
        if(category_id != null) {
            Category category = categoryServices.getCategoryById(category_id);
            if(category == null) model.addAttribute("category", new Category());
            else model.addAttribute("category", category);
        }
    }

    private Category updateCategoryMapping(Category db_category, Category web_category) {
        if(web_category.getCategory_name() != null && !web_category.getCategory_name().equals("")) db_category.setCategory_name(web_category.getCategory_name());
        if(!web_category.getResources().isEmpty()) db_category.getResources().addAll(web_category.getResources());
        return db_category;
    }


    @RequestMapping(value = "/delete/{category_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@ModelAttribute("category") Category category){
        HttpStatus status = HttpStatus.OK;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("text/plain;charset=UTF-8"));
        String body = "";
        int res = categoryServices.removeCategories(category);

        if(res > 0) {
            status = HttpStatus.OK;
            body = "remove successfully";
        }
        else {
            status = HttpStatus.BAD_REQUEST;
            body = "bad request";
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<>(body,
                headers,
                status);

        return responseEntity;
    }



}
