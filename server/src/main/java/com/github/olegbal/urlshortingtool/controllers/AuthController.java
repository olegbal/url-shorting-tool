package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.domain.dto.LoginAndPasswordDto;
import com.github.olegbal.urlshortingtool.security.TokenAuthenticationService;
import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @RequestMapping(path = "login", method = RequestMethod.POST)
    private ResponseEntity logIn(HttpServletResponse httpServletResponse, @RequestBody LoginAndPasswordDto loginAndPasswordDto) {


        if (tokenAuthenticationService.checkLogin(httpServletResponse, loginAndPasswordDto)) {

            return new ResponseEntity(userService.getUserByLogin(loginAndPasswordDto.getLogin()), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(path = "register", method = RequestMethod.POST)
    private ResponseEntity register(@RequestBody LoginAndPasswordDto loginAndPasswordDto) {

        if (userService.createUser(loginAndPasswordDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);


    }
}
