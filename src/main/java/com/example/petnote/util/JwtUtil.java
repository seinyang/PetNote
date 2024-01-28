package com.example.petnote.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public static  String createJwt(String email,String secretKey,Long expiredMs){
    Claims claims = Jwts.claims();
    claims.put("email",email);

    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date((System.currentTimeMillis()+expiredMs)))
            .signWith(SignatureAlgorithm.HS256,secretKey)
            .compact();
    }

}
