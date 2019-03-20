package com.robson.hours.services.commands;

import com.robson.core.domains.Employee;
import com.robson.core.dtos.EmployeeDTO;

public interface EmployeeCommandService {

  Employee insert(EmployeeDTO employeeDTO);

  //  Employee update(Employee employee);
  //
  //  void delete(Long id);

  //  Employee fromDTO(EmployeeDTO employeeDTO);
  //
  //  Employee fromDTO(EmployeeNewDTO newDTO);
}
