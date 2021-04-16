package com.wss.authservice.util;

import com.wss.authservice.JwtProperties;
import com.wss.authservice.entity.AppUserEntity;
import com.wss.authservice.services.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {




    static public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    static public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    static public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
    }

    static private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token).getBody();
    }

    static private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public static String generateToken(UserPrincipal userPrincipal){

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", userPrincipal.getId());
        return createToken(claims, userPrincipal.getEmail());
    }

    private static String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SECRET).compact();
    }

    static public Boolean validateToken(String token, AppUserEntity appUserEntity){
        final String username = extractEmail(token);
        return (username.equals(appUserEntity.getEmail()) && !isTokenExpired(token));
    }
}
