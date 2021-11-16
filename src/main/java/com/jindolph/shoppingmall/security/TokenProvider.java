package com.jindolph.shoppingmall.security;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.jindolph.shoppingmall.entity.Member;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String create(Member member) {
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder() //
                .signWith(key) //
                .setSubject(member.getEmail()) // sub
                .setIssuer("jindolph") // iss "https://jindolph.com/issuer"
                .setIssuedAt(new Date()) // iat
                .setExpiration(expiryDate) // exp
                .compact();
    }

    public String validateAndGetUserEmail(String token) {
        return Jwts.parserBuilder() //
                .setSigningKey(key)//
                .build()//
                .parseClaimsJws(token)//
                .getBody()//
                .getSubject();
    }
}
