package com.hzy.jwtDemo.common.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static final long TTL = 10000;

    public static String createJwtToken(Integer userId, String username, Map<String, Object> map) {
        long exp = System.currentTimeMillis() + TTL;
        JwtBuilder jwtBuilder = Jwts.builder().setId(Integer.toString(userId)).setSubject(username).setClaims(map)
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"jinhe");
        String token = jwtBuilder.compact();
        return token;
    }

    public static Claims parseJwtToken(String token) throws ExpiredJwtException{
        Claims claims = Jwts.parser().setSigningKey("jinhe").parseClaimsJws(token).getBody();
        return claims;
    }

}
