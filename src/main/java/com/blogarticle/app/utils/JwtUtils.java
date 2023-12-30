package com.blogarticle.app.utils;

import com.blogarticle.app.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    public static final int JWT_TOKEN_VALIDITY = 60 * 60 * 1000;
    public static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(User user)
    {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims,user.getEmail());
    }
    public String getUsernameFromToken(String token)
    {
         Claims claims = getAllClaimsFromToken(token);
         return claims.getSubject();
    }
    public boolean validateToken(String token, User user) throws Exception {
        String username = getUsernameFromToken(token);
        return username.equals(user.getEmail()) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token)
    {
        Claims claims = getAllClaimsFromToken(token);
        return getExpirationDateFromToken(token).before(new Date());
    }
    private Date getExpirationDateFromToken(String token)
    {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }
    private String doGenerateToken(Map<String, Object> claims,String subject)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .signWith(secretKey,SignatureAlgorithm.HS512)
                .compact();
    }
    private Claims getAllClaimsFromToken(String token)
    {
         Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
         return jwsClaims.getBody();
    }
}
