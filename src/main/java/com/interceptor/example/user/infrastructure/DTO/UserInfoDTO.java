package com.interceptor.example.user.infrastructure.DTO;

public class UserInfoDTO {
  private Long id;

  private String username;

  private String name;

  private String lastname;

  private String email;

  public UserInfoDTO() {
  }

  public UserInfoDTO(String username, String name, String lastname, String email) {
    this.username = username;
    this.name = name;
    this.lastname = lastname;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUser(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "UserInfo [id=" + id + ", username=" + username + ", name=" + name + ", lastname=" + lastname + ", email=" + email
        + "]";
  }
}
