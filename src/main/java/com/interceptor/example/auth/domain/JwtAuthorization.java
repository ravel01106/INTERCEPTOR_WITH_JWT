package com.interceptor.example.auth.domain;

import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthorization {

  private final String SECRET = "My-Secret";

  private final SecretKey SECRET_KEY = new SecretKeySpec(Base64.getDecoder().decode(SECRET), SignatureAlgorithm.HS256.getJcaName());


  public String generateToken(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
            .signWith(SECRET_KEY)
            .compact();
  }

  public Boolean validateToken(String token, String username) {
    final String tokenUsername = extractUsername(token);
    return (tokenUsername.equals(username) && !isTokenExpired(token));
  }

  public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

}
