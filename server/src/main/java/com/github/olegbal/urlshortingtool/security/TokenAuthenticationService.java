package com.github.olegbal.urlshortingtool.security;

import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.security.TokenHandler;
import com.github.olegbal.urlshortingtool.security.UserAuthentication;
import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "Auth";

    private final TokenHandler tokenHandler;

    public TokenAuthenticationService(String secret, UserServiceImpl userService) {
        tokenHandler = new TokenHandler(secret, userService);
    }

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
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
