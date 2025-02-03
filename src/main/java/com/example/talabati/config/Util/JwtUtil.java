package com.example.talabati.config.Util;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MySuperSecretKey123456".repeat(4); // Adjust to at least 32 chars
    private static final Long EXPIRATION_TIME = (long) (1000 * 60 * 60);

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username, String role) {
        return Jwts.builder().setSubject(username).claim("role", role)
                .setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();

        
    }

    public boolean  isValidToken(String token, String username) {
        
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key)
    .build().parseClaimsJws(token).getBody();
    
            // Check if the token is expired
            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                return false; // Token has expired
            }
    
            // Optionally check that the username in the token matches the user
            return (username.equals(claims.getSubject()));
        } catch (JwtException | IllegalArgumentException e) {
            return false; // If any exception occurs, treat token as invalid
        }
    }
    private boolean isTokenExpired(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().
        parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
   
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        
    }
}
