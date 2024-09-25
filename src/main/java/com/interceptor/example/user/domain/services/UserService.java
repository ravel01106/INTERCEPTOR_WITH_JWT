package com.interceptor.example.user.domain.services;

import com.interceptor.example.user.domain.models.User;

public interface UserService {
  User login(User user);
  Boolean logout(User user);
  User register(User user);
  User getUserByUsername(String username);
}
