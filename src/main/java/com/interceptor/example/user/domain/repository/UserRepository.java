package com.interceptor.example.user.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.interceptor.example.user.domain.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
  User findByUsername(String username);
}
