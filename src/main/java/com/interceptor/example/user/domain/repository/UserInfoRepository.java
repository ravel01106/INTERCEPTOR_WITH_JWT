package com.interceptor.example.user.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.interceptor.example.user.domain.models.UserInfo;

public interface UserInfoRepository  extends CrudRepository<UserInfo, Long>{

}
