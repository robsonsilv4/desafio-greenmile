package com.robson.desafiogreenmile.dto.command;

import com.robson.desafiogreenmile.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCommandDTO implements Serializable {

  private static final long serialVersionUID = 3537283637312947563L;

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório!")
  private String nome;

  @Email
  @NotEmpty(message = "Email inválido!")
  private String email;

  public UsuarioCommandDTO(@NotNull Usuario usuario) {
    id = usuario.getId();
    nome = usuario.getNome();
    email = usuario.getEmail();
  }
}
