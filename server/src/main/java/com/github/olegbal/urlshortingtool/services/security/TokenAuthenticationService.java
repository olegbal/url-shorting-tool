package com.github.olegbal.urlshortingtool.services.security;

import com.github.olegbal.urlshortingtool.dto.LoginDto;
import com.github.olegbal.urlshortingtool.domain.User;
import com.github.olegbal.urlshortingtool.security.TokenHandler;
import com.github.olegbal.urlshortingtool.security.UserAuthentication;
import com.github.olegbal.urlshortingtool.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenAuthenticationService {


    private final UserService userService;

    private static final String AUTH_HEADER_NAME = "Auth";

    //TODO Get it from properties
    private final String TOKEN_SECRET = "MY_SECRET_TSSS";

    final TokenHandler tokenHandler;

    @Autowired
    public TokenAuthenticationService(UserService userService) {
        this.userService = userService;
        tokenHandler = new TokenHandler(TOKEN_SECRET, this.userService);
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
