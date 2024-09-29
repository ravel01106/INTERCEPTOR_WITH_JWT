package com.interceptor.example.auth.domain;

import java.util.Date;

public class JwtAuthorization {

  private String token;

  private Date expiration;

  private String username;

  public JwtAuthorization(String token, Date expiration, String username) {
    this.token = token;
    this.expiration = expiration;
    this.username = username;
  }

  public JwtAuthorization() {
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getExpiration() {
    return expiration;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((token == null) ? 0 : token.hashCode());
    result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    JwtAuthorization other = (JwtAuthorization) obj;
    if (token == null) {
      if (other.token != null)
        return false;
    } else if (!token.equals(other.token))
      return false;
    if (expiration == null) {
      if (other.expiration != null)
        return false;
    } else if (!expiration.equals(other.expiration))
      return false;
    return true;
  }


}
