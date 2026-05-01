package com.ecommerce.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY="mySecretKeywhdguhbfwiubijasnbcijasbfuhwbkasmklckbasncbajnnvksdn";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    //extract Username
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    //extract all claims
    private Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    //validate token
    public boolean isTokenValid(String token, String username){
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    //validate token expiry
    private boolean isTokenExpired(String token){

        Date expiration = extractClaims(token).getExpiration();
        Date now = new Date();

        System.out.println("Expiry: " + expiration);
        System.out.println("Now: " + now);

        return expiration.before(now);


//        return extractClaims(token).getExpiration().before(new Date());
    }
}
