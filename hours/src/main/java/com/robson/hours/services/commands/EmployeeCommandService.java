package com.robson.hours.services.commands;

import com.robson.core.domains.Employee;

public interface EmployeeCommandService {
  Employee insert(Employee employee);

  Employee update(Employee employee);

  void delete(Long id);

  //  Employee fromDTO(EmployeeDTO employeeDTO);
  //
  //  Employee fromDTO(EmployeeNewDTO newDTO);
}
