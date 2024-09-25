package com.interceptor.example.user.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.interceptor.example.user.domain.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
  User findByUsername(String username);
}
