package com.itlize.Korera.controller;


import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public User findById(@PathVariable("user_id")Integer user_id){
        if(userServices == null) return new User();

        User user = userServices.getUserById(user_id);

        return user;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(User user){

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        user.setJoin_date(new java.sql.Date(millis));

        if(userServices.addUser(user) == -1){
            return "error";
        }

        return "redirect:/user/findAll";
    }

    @RequestMapping(value = "/remove/{user_id}", method = RequestMethod.DELETE)
    public String removeUser(@PathVariable("user_id") Integer user_id){
//        if(userServices == null) return "error";
//        User user = userServices.getUserById(user_id);
//        userServices.removeUser(user_id);
        return "redirect:/user/findAll";
    }

    @RequestMapping(value = "/update/{user_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("user_id")Integer user_id, User user){
        if(userServices == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userServices.saveOrUpdateUser(user);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @ModelAttribute
    public void preUpdateUser(@RequestParam(value = "user_id", required = false) Integer user_id, Model model) {
        if(user_id != null) {
            User user = userServices.getUserById(user_id);
            model.addAttribute("user", user);
        }
    }
}
