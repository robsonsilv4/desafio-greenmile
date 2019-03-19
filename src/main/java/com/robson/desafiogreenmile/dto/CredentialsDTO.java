package com.robson.desafiogreenmile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CredentialsDTO implements Serializable {

  private static final long serialVersionUID = 1501674406590648943L;

  private String email;
  private String password;
}
