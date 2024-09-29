package com.interceptor.example.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interceptor.example.user.domain.models.User;
import com.interceptor.example.user.domain.repository.UserRepository;
import com.interceptor.example.user.domain.services.UserService;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public User login(User user) {
    User userInDB = userRepository.findByUsername(user.getUsername());
    if (userInDB == null) {
      return null;
    }

    if (!userInDB.getPassword().equals(user.getPassword())) {
      return null;
    }

    return userInDB;
  }

  @Override
  public Boolean logout(User user) {
    User userInDB = userRepository.findByUsername(user.getUsername());
    return userInDB != null;
  }

  @Override
  public User register(User user) {
    User newUser = userRepository.save(user);
    return newUser;
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

}
