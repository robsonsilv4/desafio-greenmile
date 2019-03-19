package com.robson.desafiogreenmile.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

  public static UserSecurityDetails authenticated() {
    try {
      return (UserSecurityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception e) {
      return null;
    }
  }
}
