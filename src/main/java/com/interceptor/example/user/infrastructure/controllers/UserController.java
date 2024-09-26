package com.interceptor.example.user.infrastructure.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.interceptor.example.auth.application.AuthService;
import com.interceptor.example.auth.domain.JwtAuthorization;
import com.interceptor.example.user.domain.errors.AlrreadyExistsException;
import com.interceptor.example.user.domain.errors.ErrorCreateException;
import com.interceptor.example.user.domain.errors.NotFoundException;
import com.interceptor.example.user.domain.models.User;
import com.interceptor.example.user.domain.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

  @Autowired
  UserService userService;

  @Autowired
  AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<JwtAuthorization> login(@RequestBody User user) throws NotFoundException {
      User userLogin = userService.login(user);

      if (userLogin == null){
        throw new NotFoundException("User incorrect");
      }

      JwtAuthorization jwtAuthorization = authService.generateToken(userLogin.getUsername());
      return new ResponseEntity<JwtAuthorization>(jwtAuthorization, HttpStatus.OK);
  }

  @PostMapping("/api/v1/logout")
  public ResponseEntity<String> logout(@RequestBody User user) throws NotFoundException {
      Boolean isUserLogout = userService.logout(user);

      if (isUserLogout == null){
        throw new NotFoundException("User incorrect");
      }

      return new ResponseEntity<String>("The user is logout correctly", HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<JwtAuthorization> register(@RequestBody User user) throws ErrorCreateException, AlrreadyExistsException {
    User userFindByUsername = userService.getUserByUsername(user.getUsername());

    if (userFindByUsername != null) {
      throw new AlrreadyExistsException("The user already exists");
    }

    User userRegister = userService.register(user);

    if (userRegister == null){
        throw new ErrorCreateException("ERROR register user");
    }

    JwtAuthorization jwtAuthorization = authService.generateToken(userRegister.getUsername());
    return new ResponseEntity<JwtAuthorization>(jwtAuthorization, HttpStatus.CREATED);
  }

}
