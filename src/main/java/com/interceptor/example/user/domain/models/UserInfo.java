package com.interceptor.example.user.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_info")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "userinfo_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "username", referencedColumnName = "username")
  private User user;

  private String name;

  private String lastname;

  private String email;

  public UserInfo() {
  }

  public UserInfo(User user, String name, String lastname, String email) {
    this.user = user;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserInfo [id=" + id + ", user=" + user + ", name=" + name + ", lastname=" + lastname + ", email=" + email
        + "]";
  }

}
