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
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("CategoryServicesImpl")
    private CategoryServices categoryServices;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Category> findAll() {
        if(categoryServices == null) return new ArrayList<>();
        List<Category> categories = categoryServices.listCategories();
        return categories;
    }

    @RequestMapping(value = "/findById/{category_id}", method = RequestMethod.GET)
    public Category findByID(@PathVariable("category_id")Integer category_id) {
        if(categoryServices == null || category_id == null) return new Category();
        Category category = categoryServices.getCategoryById(category_id);
        return category == null ? new Category() : category;
    }

    @RequestMapping(value = "/findByName/{category_name}", method = RequestMethod.GET)
    public List<Category> findByName (@PathVariable("category_name")String category_name) {
        if(categoryServices == null) return new ArrayList<>();
        List<Category> categories = categoryServices.getCategoryByName(category_name);
        return categories;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Category> addCategory(RequestEntity<Category> requestEntity){
        Category category = requestEntity.getBody();

        HttpStatus status;

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));
        int res = -1;

        if(category != null) {
            res = categoryServices.saveOrUpdateCategory(category);
        }

        if(res > 0) status = HttpStatus.OK;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Category> responseEntity = new ResponseEntity<>(category, headers, status);

        return responseEntity;
    }


    @RequestMapping(value = "/update/{category_id}", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@ModelAttribute("category") Category category, RequestEntity<Category> requestEntity){
        category = updateCategoryMapping(category, requestEntity.getBody());

        HttpStatus status;

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = categoryServices.saveOrUpdateCategory(category);

        if(res == -1) status = HttpStatus.NOT_ACCEPTABLE;
        else if(res == -2) status = HttpStatus.NOT_ACCEPTABLE;
        else if(res > 0) status = HttpStatus.OK;
        else  status = HttpStatus.NOT_ACCEPTABLE;

        ResponseEntity<Category> responseEntity = new ResponseEntity<>(category, headers, status);

        return responseEntity;
    }

    @ModelAttribute(value = "/update/{category_id}")
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
    public ResponseEntity<Category> deleteCategory(@ModelAttribute("category") Category category){
        HttpStatus status;

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));
        int res = categoryServices.removeCategories(category);

        if(res > 0) status = HttpStatus.OK;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Category> responseEntity = new ResponseEntity<>(category, headers, status);

        return responseEntity;
    }

    @RequestMapping(value = "/pagination/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> pagination(@PathVariable("page") String page) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        if(categoryServices == null) return new ResponseEntity(new ArrayList<>(), headers, status);

        String[] strs = page.split("-");

        List<Category> list = new ArrayList<>();

        try{
            Integer start = Integer.valueOf(strs[0]);
            Integer size = Integer.valueOf(strs[1]);
            status = HttpStatus.OK;
            list = categoryServices.paginationCategory(start, size);
        }catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;

        }

        ResponseEntity<List<Category>> responseEntity = new ResponseEntity<>(list, headers, status);

        return responseEntity;
    }

}
