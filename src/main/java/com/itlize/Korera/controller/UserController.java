package com.itlize.Korera.controller;


import com.itlize.Korera.entities.Project;
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

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired(required=false)
    @Qualifier("UserServicesImpl")
    private UserServices userServices;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll(){
        if(userServices == null) return new ArrayList<>();

        List<User> users = userServices.listUsers();

        return users;
    }

    @RequestMapping(value = "/findById/{user_id}", method = RequestMethod.GET)
    @ResponseBody
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addUser(RequestEntity<User> requestEntity){

        User user = requestEntity.getBody();

        long millis = System.currentTimeMillis();
        user.setJoin_date(new java.sql.Date(millis));

        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));
//        headers.put("Access-Control-Allow-Origin", Arrays.asList("origin"));
        headers.put("origin", Arrays.asList("localhost:8080"));
        headers.put("Access-Control-Allow-Methods", Arrays.asList("POST", "GET", "OPTIONS"));
        headers.put("Access-Control-Allow-Credentials", Arrays.asList("true"));
        headers.put("Access-Control-Allow-Headers", Arrays.asList("content-type", "x-gwt-module-base", "x-gwt-permutation", "clientid", "longpush"));

        int res = userServices.addUser(user);
        String body = "";
        if(res < 0) status = HttpStatus.NOT_ACCEPTABLE;
        else status = HttpStatus.CREATED;

        body = res > 0 ? "add user successful" : "not acceptable request for adding user";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(body,
                headers,
                status);

        return responseEntity;
    }

    @RequestMapping(value = "/remove/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@ModelAttribute("user") User user){
        HttpStatus status = HttpStatus.OK;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("text/plain;charset=UTF-8"));
        String body = "";

        int res = userServices.removeUser(user);

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

    @RequestMapping(value = "/update/{user_id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@ModelAttribute("user") User user, RequestEntity<User> requestEntity){
        user = updateUserMapping(user, requestEntity.getBody());

        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("text/plain;charset=UTF-8"));
        String body = "";

        int res = userServices.saveOrUpdateUser(user);

        if(res > 0) {
            status = HttpStatus.OK;
            body = "update successfully";
        }
        else if(res == -1){
            status = HttpStatus.NOT_ACCEPTABLE;
            body = "the user information is not acceptable";
        }else if(res == -2) {
            status = HttpStatus.NOT_ACCEPTABLE;
            body = "the user name is already exist";
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<>(body,
                headers,
                status);
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


}
