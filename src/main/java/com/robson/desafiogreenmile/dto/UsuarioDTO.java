package com.robson.desafiogreenmile.dto;

import com.robson.desafiogreenmile.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 3537283637312947563L;

    private Long id;

    @Length
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String nome;

    @Email
    @NotEmpty(message = "Email inválido!")
    private String email;

    public UsuarioDTO(@NotNull Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
    }
}
