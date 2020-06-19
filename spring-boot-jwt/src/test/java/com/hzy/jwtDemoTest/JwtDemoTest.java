package com.hzy.jwtDemoTest;

import com.hzy.jwtDemo.common.utils.JWTUtil;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;

public class JwtDemoTest {

    public static void main(String[] args) {
        test();
//        String token = createSignCustom();
//        parseSign(token);
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4OCIsInN1YiI6InRlc3ROYW1lIiwiaWF0IjoxNTY2MjA0NTE2fQ.hkyzv4dsBgDLn85sMZRMezYBsq4oxt2NpNHNOHWSusw
    }

    static String createSign() {
        JwtBuilder jwtBuilder = Jwts.builder().setId("88").setSubject("testName")
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"testKey");
        String token = jwtBuilder.compact();
        System.out.println(token);
        return token;
    }

    static void parseSign (String token) {
        Claims claims = Jwts.parser().setSigningKey("testKey").parseClaimsJws(token).getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.get("testId"));
        System.out.println(claims.get("sessionId"));
    }

    /**
     * 自定义sign中的内容
     * @return
     */
    static String createSignCustom () {
        JwtBuilder jwtBuilder = Jwts.builder().setId("88").setSubject("testName")
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"testKey")
                .claim("testId","1234").claim("sessionId","123412341234");
        String token = jwtBuilder.compact();
        System.out.println(token);
        return token;
    }

    public static void test() {
        String token = JWTUtil.createJwtToken(123,"testname",new HashMap<>());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Claims claims = null;
        try {
            claims = JWTUtil.parseJwtToken(token);
            System.out.println(claims.getId());
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("已过期");
        }
    }
}
