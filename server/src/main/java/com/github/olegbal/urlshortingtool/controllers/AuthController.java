package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.domain.dto.LoginDto;
import com.github.olegbal.urlshortingtool.domain.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import com.github.olegbal.urlshortingtool.services.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController("authController")
@RequestMapping("/api/v1/")
public class AuthController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public ResponseEntity logIn(HttpServletResponse httpServletResponse, @RequestBody LoginDto loginAndPasswordDto) {


        if (tokenAuthenticationService.checkLogin(httpServletResponse, loginAndPasswordDto)) {

            return new ResponseEntity(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationDto registrationDto) {

        if (userServiceImpl.createUser(registrationDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);


    }
}
