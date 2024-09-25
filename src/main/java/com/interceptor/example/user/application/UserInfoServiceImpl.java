package com.interceptor.example.user.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interceptor.example.user.domain.models.User;
import com.interceptor.example.user.domain.models.UserInfo;
import com.interceptor.example.user.domain.repository.UserInfoRepository;
import com.interceptor.example.user.domain.repository.UserRepository;
import com.interceptor.example.user.domain.services.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

  @Autowired
  private UserInfoRepository userInfoRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<UserInfo> getAllUsersInformation() {
    List<UserInfo> allUsersInfo = (List<UserInfo>) userInfoRepository.findAll();
    return allUsersInfo;
  }

  @Override
  public UserInfo getUserInformationByUsername(String username) {
    User userByUsername = userRepository.findByUsername(username);

    if (userByUsername == null) {
      return null;
    }

    List<UserInfo> usersInfoByUser = userInfoRepository.findByUser(userByUsername);

    if (usersInfoByUser.isEmpty()){
      return null;
    }
    return usersInfoByUser.get(0);
  }

  @Override
  public UserInfo createUserInfo(UserInfo userInfo) {
    UserInfo newUserInfo = userInfoRepository.save(userInfo);
    return newUserInfo;
  }

}
