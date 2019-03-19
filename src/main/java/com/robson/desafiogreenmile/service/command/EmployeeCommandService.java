package com.robson.desafiogreenmile.service.command;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.dto.EmployeeNewDTO;
import com.robson.desafiogreenmile.dto.EmployeeDTO;

public interface EmployeeCommandService {
  Employee insert(Employee employee);

  Employee update(Employee employee);

  void delete(Long id);

  // Métodos auxiliares
  Employee fromDTO(EmployeeDTO employeeDTO);

  Employee fromDTO(EmployeeNewDTO novoDTO);
}
