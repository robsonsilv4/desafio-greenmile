package com.robson.hours.services.commands;

import com.robson.core.domains.Employee;
import com.robson.core.repositories.EmployeeRepository;
import com.robson.hours.services.queries.EmployeeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeCommandServiceImpl {

  //  private final BCryptPasswordEncoder passwordEncoder;
  private final EmployeeRepository employeeRepository;
  private final EmployeeQueryService employeeService;

  public Employee insert(Employee employee) {
    return employeeRepository.save(employee);
  }
}
