package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserServiceImpl userService;

    @PreAuthorize("@userPreAuthorizeService.checkRightsToUrlByUsername(#request,#userName)")
    @RequestMapping(path = "/api/v1/account", params = "userName", method = RequestMethod.GET)
    public ResponseEntity getUserDetails(HttpServletRequest request, @RequestParam("userName") String userName) {

        return new ResponseEntity(userService.getUserByLogin(userName), HttpStatus.OK);
    }

}
