package com.interceptor.example.user.infrastructure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interceptor.example.user.domain.errors.UserException;
import com.interceptor.example.user.domain.models.User;
import com.interceptor.example.user.domain.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User user) throws UserException {
      User userLogin = userService.login(user);

      if (userLogin == null){
        throw new UserException("User incorrect");
      }

      return new ResponseEntity<User>(userLogin, HttpStatus.OK);
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(@RequestBody User user) throws UserException {
      Boolean isUserLogout = userService.logout(user);

      if (isUserLogout == null){
        throw new UserException("User incorrect");
      }

      return new ResponseEntity<String>("The user is logout correctly", HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody User user) throws UserException {
    User userFindByUsername = userService.getUserByUsername(user.getUsername());

    if (userFindByUsername == null) {
      throw new UserException("The user already exists");
    }

    User userRegister = userService.register(user);

    if (userRegister == null){
        throw new UserException("ERROR register user");
    }

    return new ResponseEntity<User>(userRegister, HttpStatus.CREATED);
  }

}
