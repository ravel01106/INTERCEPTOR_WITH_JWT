package com.interceptor.example.user.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.interceptor.example.user.domain.models.User;
import com.interceptor.example.user.domain.models.UserInfo;
import java.util.List;


@Repository
public interface UserInfoRepository  extends CrudRepository<UserInfo, Long>{
  List<UserInfo> findByUser(User user);
}
