package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.services.UserService;
import com.github.olegbal.urlshortingtool.services.impl.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("customUserService") UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("@userPreAuthorizeService.checkRightsToUrlByUsername(#request,#userName)")
    @RequestMapping(path = "/api/v1/account", params = "userName", method = RequestMethod.GET)
    public ResponseEntity getUserDetails(HttpServletRequest request, @RequestParam("userName") String userName) {

        return new ResponseEntity(userService.getUserByLogin(userName), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/v1/users", method = RequestMethod.GET)
    public ResponseEntity getRegisteredUsers() {

        return new ResponseEntity(userService.getRegisteredUsers(), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/v1/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable long id) {

        if (userService.deleteUser(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
}
