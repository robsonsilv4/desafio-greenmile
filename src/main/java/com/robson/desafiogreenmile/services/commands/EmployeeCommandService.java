package com.robson.desafiogreenmile.services.commands;

import com.robson.desafiogreenmile.domains.Employee;
import com.robson.desafiogreenmile.dtos.EmployeeNewDTO;
import com.robson.desafiogreenmile.dtos.EmployeeDTO;

public interface EmployeeCommandService {
  Employee insert(Employee employee);

  Employee update(Employee employee);

  void delete(Long id);

  // Métodos auxiliares
  Employee fromDTO(EmployeeDTO employeeDTO);

  Employee fromDTO(EmployeeNewDTO newDTO);
}
