package com.github.olegbal.urlshortingtool.services.security;

import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("userPreAuthorizeService")
public class UserPreAuthorizeService {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private UserRepository userRepository;

    public boolean checkRightsToUrlById(HttpServletRequest request, long id) {

        User requestingUser = tokenAuthenticationService.tokenHandler.parseUserFromToken(request.getHeader("Auth"));


        if (requestingUser != null) {
            if (requestingUser.getUserId() == id) {
                return true;
            }
        }

        return false;
    }

   public  boolean checkRightsToUrlByUsername(HttpServletRequest request, String userName) {

        User requestingUser = tokenAuthenticationService.tokenHandler.parseUserFromToken(request.getHeader("Auth"));


        if (requestingUser != null) {
            if (requestingUser.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
