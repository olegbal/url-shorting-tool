package com.github.olegbal.urlshortingtool.security;

import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.services.impl.CustomUserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class TokenHandler {

    private final String secret;
    private final CustomUserService userService;


    public TokenHandler(String secret, CustomUserService userService) {
        this.secret = secret;
        this.userService = userService;
    }

    public User parseUserFromToken(String token) {

        try {
            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return userService.loadUserByUsername(username);
        } catch (ExpiredJwtException ex) {
            return null;
        }

    }

    public String createTokenForUser(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(1l));
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}