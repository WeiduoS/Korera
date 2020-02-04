package com.itlize.Korera.controller;


import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.UserServices;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required=false)
    @Qualifier("UserServicesImpl")
    private UserServices userServices;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll(){
        if(userServices == null) return new ArrayList<>();

        List<User> users = userServices.listUsers();

        return users;
    }

    @RequestMapping(value = "/findById/{user_id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("user_id")Integer user_id){
        HttpStatus status = HttpStatus.NOT_FOUND;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        if(userServices == null || user_id == null) new ResponseEntity<>(new User(),
                headers,
                status);

        User user = userServices.getUserById(user_id);

        if(user != null) status = HttpStatus.OK;
        else status = HttpStatus.NOT_FOUND;


        ResponseEntity<User> responseEntity = new ResponseEntity<>(user,
                headers,
                status);
        return responseEntity;
    }

    @RequestMapping(value = {"/add", "/sign-up"}, method = RequestMethod.POST)
    public ResponseEntity<User> addUser(RequestEntity<User> requestEntity){
        User user = requestEntity.getBody();

        long millis = System.currentTimeMillis();
        user.setJoin_date(new java.sql.Date(millis));

        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = userServices.addUser(user);
        if(res < 0) status = HttpStatus.NOT_ACCEPTABLE;
        else status = HttpStatus.CREATED;

        ResponseEntity<User> responseEntity = new ResponseEntity(user,
                headers,
                status);

        return responseEntity;
    }

    @RequestMapping(value = "/remove/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> removeUser(@ModelAttribute("user") User user){
        HttpStatus status;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = userServices.removeUser(user);

        if(res > 0) status = HttpStatus.OK;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, headers, status);

        return responseEntity;
    }

    @RequestMapping(value = "/update/{user_id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@ModelAttribute("user") User user, RequestEntity<User> requestEntity){
        user = updateUserMapping(user, requestEntity.getBody());

        HttpStatus status;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = userServices.saveOrUpdateUser(user);

        if(res > 0) status = HttpStatus.OK;
        else if(res == -1)status = HttpStatus.NOT_ACCEPTABLE;
        else if(res == -2) status = HttpStatus.NOT_ACCEPTABLE;
        else status = HttpStatus.NOT_ACCEPTABLE;

        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, headers, status);
        return responseEntity;

    }

    private User updateUserMapping(User db_user, User web_user) {
        if(web_user.getUser_name() != null) db_user.setUser_name(web_user.getUser_name());
        if(web_user.getPassword() != null) db_user.setPassword(web_user.getPassword());
        if(!web_user.getRoles().isEmpty()) db_user.getRoles().addAll(web_user.getRoles());
        if(!web_user.getProjects().isEmpty())db_user.getProjects().addAll(web_user.getProjects());
        if(web_user.getIcon() != null) db_user.setIcon(web_user.getIcon());

        return db_user;
    }

    @ModelAttribute(value = "/*/{user_id}")
    public void preUserRequest(@RequestParam(value = "user_id", required = false) Integer user_id, Model model) {
        if(user_id != null) {
            User user = userServices.getUserById(user_id);
            if(user == null) model.addAttribute("user", new Project());
            else model.addAttribute("user", user);
        }
    }

    @RequestMapping(value = "/pagination/{page}",method = RequestMethod.GET)
    public ResponseEntity<List<User>> pagination(@PathVariable("page") String page){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        if(userServices == null) return new ResponseEntity<>(new ArrayList<>(), headers, status);

        String[] strs = page.split("-");
        List<User> users = new ArrayList<>();

        try{
            Integer start = Integer.valueOf(strs[0]);
            Integer size = Integer.valueOf(strs[1]);
            status = HttpStatus.OK;
            users = userServices.paginationUser(start, size);
        }catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;

        }

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(users,
                headers,
                status);
        return responseEntity;
    }


}
