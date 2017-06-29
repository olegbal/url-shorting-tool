package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.dto.LoginDto;
import com.github.olegbal.urlshortingtool.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.services.UserService;
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

//FIXMe remove dat \n->
@RestController("authController")
@RequestMapping("/api/v1/")
public class AuthController {

    private final UserService userService;

    private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public AuthController(@Qualifier("customUserService") UserService userService, TokenAuthenticationService tokenAuthenticationService) {
        this.userService = userService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public ResponseEntity logIn(HttpServletResponse httpServletResponse, @RequestBody LoginDto loginAndPasswordDto) {


        if (tokenAuthenticationService.checkLogin(httpServletResponse, loginAndPasswordDto)) {

            return new ResponseEntity<>(userService.
                    getUserByLogin(loginAndPasswordDto.
                            getLogin()).getRoles(),
                    HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationDto registrationDto) {

        if (userService.createUser(registrationDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);


    }
}
