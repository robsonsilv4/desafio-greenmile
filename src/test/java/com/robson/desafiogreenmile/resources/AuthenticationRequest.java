package com.robson.desafiogreenmile.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
  private String email;
  private String senha;
}
