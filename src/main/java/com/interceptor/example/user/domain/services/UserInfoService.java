package com.interceptor.example.user.domain.services;

import java.util.List;

import com.interceptor.example.user.domain.models.UserInfo;

public interface UserInfoService {

  List<UserInfo> getAllUsersInformation();

  UserInfo getUserInformationByUsername();

  UserInfo createUserInfo(UserInfo userInfo);

}
