package com.itlize.Korera.controller;


import com.itlize.Korera.entities.Project;
import com.itlize.Korera.entities.User;
import com.itlize.Korera.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired(required=false)
    @Qualifier("UserServicesImpl")
    private UserServices userServices;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        if(userServices == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<User> users = userServices.listUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("user_id")Integer user_id){
        if(userServices == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = userServices.getUserById(user_id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(User user){

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        user.setJoin_date(new java.sql.Date(millis));

        if(userServices.addUser(user) == -1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }

        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/remove/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeUser(@PathVariable("user_id") Integer user_id){
        if(userServices == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = userServices.getUserById(user_id);

        userServices.removeUser(user_id);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{user_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("user_id")Integer user_id, User user){
        if(userServices == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userServices.saveOrUpdateUser(user);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

//    @ModelAttribute
//    public void preUpdateUser(@RequestParam(value = "user_id", required = false) Integer user_id, Model model) {
//        if(user_id != null) {
//            User user = userServices.getUserById(user_id);
//            model.addAttribute("user", user);
//        }
//    }
}
