package com.interceptor.example.auth.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.interceptor.example.auth.domain.JwtAuthorization;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

  private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
  private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);;


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
    logger.info("is null?: " + jws);
    if (jws != null) {
      Boolean isCorrectUsername = jws.getBody().getSubject().equals(jwtAuthorizationCheck.getUsername());
      logger.info("is correct username?: " + isCorrectUsername);
      Boolean isNotExpired = (jws.getBody().getExpiration().compareTo(new Date()) > 0);
        logger.info("is not expired?: " + isNotExpired);


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
