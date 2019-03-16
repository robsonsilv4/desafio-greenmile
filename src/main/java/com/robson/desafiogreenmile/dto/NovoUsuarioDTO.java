package com.robson.desafiogreenmile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NovoUsuarioDTO implements Serializable {

  private static final long serialVersionUID = 4224057337516317769L;

  private Long id;
  private String nome;
  private String email;

  @NotEmpty(message = "Preenchimento obrigatório!")
  private String senha;
}
