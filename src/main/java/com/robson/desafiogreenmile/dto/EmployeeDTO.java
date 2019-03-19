package com.robson.desafiogreenmile.dto;

import com.robson.desafiogreenmile.domain.Employee;
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
public class EmployeeDTO implements Serializable {

  private static final long serialVersionUID = 3537283637312947563L;

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório!")
  private String nome;

  @Email
  @NotEmpty(message = "Email inválido!")
  private String email;

  public EmployeeDTO(@NotNull Employee employee) {
    id = employee.getId();
    nome = employee.getName();
    email = employee.getEmail();
  }
}
