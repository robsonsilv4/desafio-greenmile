package com.robson.desafiogreenmile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
  private String email;
  private String senha;
}
