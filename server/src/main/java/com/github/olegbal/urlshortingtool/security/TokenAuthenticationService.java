package com.github.olegbal.urlshortingtool.security;

import com.github.olegbal.urlshortingtool.domain.dto.LoginAndPasswordDto;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationService {


    @Qualifier("userDetailsService")
    @Autowired
    private UserServiceImpl userService;

    private static final String AUTH_HEADER_NAME = "Auth";

    private final TokenHandler tokenHandler;

    public TokenAuthenticationService(String secret, UserServiceImpl userService) {
        tokenHandler = new TokenHandler(secret, userService);
    }

    public boolean checkLogin(HttpServletResponse response, LoginAndPasswordDto authCheckDto) {

        User user = userService.loadUserByUsername(authCheckDto.getLogin());
        if (user != null) {
            if (user.getPassword().equals(authCheckDto.getPassword())) {
                response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
                return true;
            }
        }
        return false;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
