package com.itlize.Korera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Weiduo
 * @date 2019/12/29 - 2:41 PM
 */
@Controller
@RequestMapping(path="/user")
public class HelloController {
    /*
     *
     *
     */
    @RequestMapping(path="/hello")
    public String sayHello() {
        System.out.println("Hello Itlize!!!!");
        return "success";
    }
    @RequestMapping(path = "/testReqestMapping", method = {RequestMethod.GET}, params = {"username"}, headers = {"Accept"})
    public String testRequestMapping(String username) {
        System.out.println("test Request Mapping" + username);
        return "success";
    }
}
