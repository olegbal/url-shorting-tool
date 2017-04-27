package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/")
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserServiceImpl userService;

//    @RequestMapping(path = "user", method = RequestMethod.POST)
//    private ResponseEntity addNewUser() {
//
//        return new ResponseEntity(HttpStatus.OK);
//    }

}