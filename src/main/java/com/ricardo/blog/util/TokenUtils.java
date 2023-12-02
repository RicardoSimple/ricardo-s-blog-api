package com.ricardo.blog.util;

import com.ricardo.blog.model.RefreshToken;
import com.ricardo.blog.model.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class TokenUtils {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME_MS = 3600000; // 1 hour
    private static final int TOKEN_EXPIRE_HOUR = 12;
    private static final int REFRESH_TOKEN_EXPIRE_DAY = 7;
    public static String generateToken(TokenInfo tokenInfo) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME_MS*TOKEN_EXPIRE_HOUR);

        return Jwts.builder()
                .claim("userName", tokenInfo.getUserName())
                .claim("id", tokenInfo.getId())
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String generateRefreshToken(RefreshToken refreshToken) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME_MS*24*REFRESH_TOKEN_EXPIRE_DAY);

        return Jwts.builder()
                .claim("secret", refreshToken.getSecret())
                .claim("id", refreshToken.getId())
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SECRET_KEY)
                .compact();
    }
    public static RefreshToken parseRefreshToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        RefreshToken tokenInfo = new RefreshToken();
        tokenInfo.setSecret((String) claims.get("secret"));
        tokenInfo.setId(claims.get("id",Long.class));

        return tokenInfo;
    }
    public static TokenInfo parseToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserName((String) claims.get("userName"));
        tokenInfo.setId(claims.get("id",Long.class));

        return tokenInfo;
    }

    public static void main(String[] args) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserName("your_username");
        tokenInfo.setId(123L);

        String token = generateToken(tokenInfo);
        System.out.println("Generated Token: " + token);

        TokenInfo parsedTokenInfo = parseToken(token);
        System.out.println("Parsed Token Info:");
        System.out.println("User Name: " + parsedTokenInfo.getUserName());
        System.out.println("ID: " + parsedTokenInfo.getId());
    }
}
