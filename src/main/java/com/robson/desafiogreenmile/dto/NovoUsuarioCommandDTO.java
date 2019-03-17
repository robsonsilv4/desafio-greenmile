package com.robson.desafiogreenmile.dto.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NovoUsuarioCommandDTO implements Serializable {

  private static final long serialVersionUID = 916643947514327981L;

  private String nome;
  private String email;
  private String senha;
}
