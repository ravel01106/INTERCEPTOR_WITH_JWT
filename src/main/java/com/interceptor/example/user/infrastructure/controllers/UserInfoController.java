package com.interceptor.example.user.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interceptor.example.user.domain.errors.AlrreadyExistsException;
import com.interceptor.example.user.domain.errors.ErrorCreateException;
import com.interceptor.example.user.domain.errors.NotFoundException;
import com.interceptor.example.user.domain.models.User;
import com.interceptor.example.user.domain.models.UserInfo;
import com.interceptor.example.user.domain.services.UserInfoService;
import com.interceptor.example.user.domain.services.UserService;
import com.interceptor.example.user.infrastructure.DTO.UserInfoDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/usersinfo")
public class UserInfoController {

  @Autowired
  private UserInfoService userInfoService;

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<UserInfo>> getAllUsersInfo() {
    List<UserInfo> usersInfo = userInfoService.getAllUsersInformation();
      return new ResponseEntity<>(usersInfo, HttpStatus.OK);
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<UserInfo> getUserInfoByUsername(@PathVariable String username) throws NotFoundException {
      UserInfo userInfo = userInfoService.getUserInformationByUsername(username);

      if (userInfo == null){
        throw new NotFoundException("This user does not exists");
      }

      return new ResponseEntity<>(userInfo, HttpStatus.OK);
  }

  @PostMapping("/user")
  public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfoDTO userInfoDTO) throws AlrreadyExistsException, ErrorCreateException, NotFoundException {
    User userInDB = userService.getUserByUsername(userInfoDTO.getUsername());

    if (userInDB == null){
      throw new NotFoundException("This user does not exists");
    }

    UserInfo userInfoInDB = userInfoService.getUserInformationByUsername(userInfoDTO.getUsername());

    if (userInfoInDB != null){
      throw new AlrreadyExistsException("This user information already exists");
    }

    UserInfo newUserInfo = new UserInfo(userInDB, userInfoDTO.getName(), userInfoDTO.getLastname(), userInfoDTO.getEmail());

    UserInfo userInfoResultOperationCreated = userInfoService.createUserInfo(newUserInfo);

    if (userInfoResultOperationCreated == null) {
      throw new ErrorCreateException("ERROR to create user information");
    }

      return new ResponseEntity<>(newUserInfo, HttpStatus.CREATED);
  }
}
