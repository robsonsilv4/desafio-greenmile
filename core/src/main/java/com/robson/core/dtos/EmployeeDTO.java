package com.robson.core.dtos;

import com.robson.core.domains.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório!")
  private String username;

  public EmployeeDTO(@NotNull Employee employee) {
    id = employee.getId();
    username = employee.getUsername();
  }
}
