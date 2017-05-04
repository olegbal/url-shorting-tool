package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PreAuthorize("@userPreAuthorizeService.checkRightsToUrlByUsername(#request,#userName)")
    @RequestMapping(path = "/api/v1/account", params = "userName", method = RequestMethod.GET)
    public ResponseEntity getUserDetails(HttpServletRequest request, @RequestParam("userName") String userName) {

        return new ResponseEntity(userServiceImpl.getUserByLogin(userName), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/v1/users",method = RequestMethod.GET)
    public ResponseEntity getRegisteredUsers() {

        return new ResponseEntity(userServiceImpl.getRegisteredUsers(), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/v1/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable long id) {

        if (userServiceImpl.deleteUser(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
}
