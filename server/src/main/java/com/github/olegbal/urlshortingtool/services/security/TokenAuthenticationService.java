package com.github.olegbal.urlshortingtool.services.security;

import com.github.olegbal.urlshortingtool.domain.dto.LoginDto;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.security.TokenHandler;
import com.github.olegbal.urlshortingtool.security.UserAuthentication;
import com.github.olegbal.urlshortingtool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationService {

    @Qualifier("customUserService")
    private final UserService userService;

    private static final String AUTH_HEADER_NAME = "Auth";

    final TokenHandler tokenHandler;

    public TokenAuthenticationService(String secret, @Autowired UserService userService) {
        this.userService = userService;
        tokenHandler = new TokenHandler(secret, this.userService);
    }

    public boolean checkLogin(HttpServletResponse response, LoginDto authCheckDto) {

        User user = (User) userService.loadUserByUsername(authCheckDto.getLogin());
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

    public TokenHandler getTokenHandler() {
        return tokenHandler;
    }
}
