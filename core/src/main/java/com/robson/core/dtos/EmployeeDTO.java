package com.robson.core.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

  private String username;
  private String password;
}
