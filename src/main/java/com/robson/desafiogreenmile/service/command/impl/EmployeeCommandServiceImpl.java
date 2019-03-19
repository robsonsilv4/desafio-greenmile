package com.robson.desafiogreenmile.service.command.impl;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.dto.EmployeeDTO;
import com.robson.desafiogreenmile.dto.EmployeeNewDTO;
import com.robson.desafiogreenmile.repository.EmployeeRepository;
import com.robson.desafiogreenmile.service.command.EmployeeCommandService;
import com.robson.desafiogreenmile.service.query.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

  @Autowired private EmployeeRepository employeeRepository;
  @Autowired private EmployeeQueryService employeeService;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Employee insert(Employee employee) {
    employee.setId(null);
    employee = employeeRepository.save(employee);
    return employee;
  }

  @Override
  public Employee update(Employee employee) {
    Employee newEmployee = employeeService.find(employee.getId());
    updateData(newEmployee, employee);
    return employeeRepository.save(newEmployee);
  }

  @Override
  public void delete(Long id) {
    employeeService.find(id);
    employeeRepository.deleteById(id);
  }

  public void updateData(Employee newEmployee, Employee employee) {
    newEmployee.setName(employee.getName());
    newEmployee.setEmail(employee.getEmail());
  }

  public Employee fromDTO(EmployeeDTO employeeDTO) {
    return new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getEmail(), null);
  }

  public Employee fromDTO(EmployeeNewDTO newDTO) {
    return new Employee(
        null,
        newDTO.getName(),
        newDTO.getEmail(),
        bCryptPasswordEncoder.encode(newDTO.getPassword()));
  }
}
