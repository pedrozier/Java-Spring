package com.standart.crudjwt.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    static final long EXPIRATION_TIME = 4_000_000; // teste 10seg 10_000
    static final String SECRET = "d3ad7e26b9eb11ec84220242ac120002";
    static final String TOKEN_PREFIX = "bearer";
    static final String HEADER_STRING = "Authorization";

    static void addAuthentcation(HttpServletResponse response, String userName) throws IOException {

        String JWT = Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        response.setHeader(HEADER_STRING, TOKEN_PREFIX + " " + EXPIRATION_TIME);

        response.setContentType("text/plain");

        response.getWriter().write(JWT);

    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                    .getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }

        return null;
    }

}
