package com.robson.desafiogreenmile.service.command.impl;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.dto.EmployeeNewDTO;
import com.robson.desafiogreenmile.dto.EmployeeDTO;
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
  @Autowired private EmployeeQueryService usuarioQuery;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Employee insert(Employee employee) {
    employee.setId(null);
    employee = employeeRepository.save(employee);
    return employee;
  }

  @Override
  public Employee update(Employee employee) {
    Employee novoEmployee = usuarioQuery.find(employee.getId());
    updateData(novoEmployee, employee);
    return employeeRepository.save(novoEmployee);
  }

  @Override
  public void delete(Long id) {
    usuarioQuery.find(id);
    employeeRepository.deleteById(id);
  }

  // Métodos auxiliares
  public void updateData(Employee novoEmployee, Employee employee) {
    novoEmployee.setName(employee.getName());
    novoEmployee.setEmail(employee.getEmail());
  }

  public Employee fromDTO(EmployeeDTO employeeDTO) {
    return new Employee(employeeDTO.getId(), employeeDTO.getNome(), employeeDTO.getEmail(), null);
  }

  public Employee fromDTO(EmployeeNewDTO novoDTO) {
    return new Employee(
        null,
        novoDTO.getNome(),
        novoDTO.getEmail(),
        bCryptPasswordEncoder.encode(novoDTO.getSenha()));
  }
}
