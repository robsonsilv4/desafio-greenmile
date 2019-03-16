package com.robson.desafiogreenmile.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

  public static UsuarioDetails authenticated() {
    try {
      return (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception e) {
      return null;
    }
  }
}
