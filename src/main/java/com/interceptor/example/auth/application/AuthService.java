package com.interceptor.example.auth.application;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.interceptor.example.auth.domain.JwtAuthorization;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

   private final String SECRET = "My-Secret";

  private final SecretKey SECRET_KEY = new SecretKeySpec(Base64.getDecoder().decode(SECRET), SignatureAlgorithm.HS256.getJcaName());


  public JwtAuthorization generateToken(String username) {

    Date currentTime = new Date(System.currentTimeMillis());
    Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);

    String token = Jwts.builder()
    .setSubject(username)
    .setIssuedAt(currentTime)
    .setNotBefore(currentTime)
    .setExpiration(expirationDate)
    .signWith(SECRET_KEY)
    .compact();

     JwtAuthorization jwtAuthorization = new JwtAuthorization(token, expirationDate, username);

    return jwtAuthorization;
  }


  public Boolean validateToken(JwtAuthorization jwtAuthorizationCheck) {


    Jws<Claims> jws = null;

    jws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtAuthorizationCheck.getToken());

    if (jws != null) {
      Boolean isCorrectUsername = jws.getBody().getSubject().equals(jwtAuthorizationCheck.getUsername());
      Boolean isNotExpired = (jws.getBody().getExpiration().compareTo(new Date()) > 0)
        && (jws.getBody().getNotBefore().compareTo(new Date()) > 0);


      return isCorrectUsername && isNotExpired;
    }
    return false;

  }

  public JwtAuthorization getJwtAuthorization(String token){
    return new JwtAuthorization(token, getBodyToken(token).getExpiration(), getBodyToken(token).getSubject());
  }


  private Claims getBodyToken(String token){
    return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
  }

}
