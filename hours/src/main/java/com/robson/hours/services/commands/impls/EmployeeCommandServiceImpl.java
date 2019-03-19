package com.robson.hours.services.commands.impls;

import com.robson.core.domains.Employee;
import com.robson.core.dtos.EmployeeDTO;
import com.robson.core.dtos.EmployeeNewDTO;
import com.robson.core.repositories.EmployeeRepository;
import com.robson.hours.services.commands.EmployeeCommandService;
import com.robson.hours.services.queries.EmployeeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeQueryService employeeService;
  // private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public Employee insert(Employee employee) {
    employee.setId(null);
    // employee.setPassword(passwordEncoder.encode(employee.getPassword()));
    return employeeRepository.save(employee);
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
    newEmployee.setUsername(employee.getUsername());
  }

  //  public Employee fromDTO(EmployeeDTO employeeDTO) {
  //    return new Employee(employeeDTO.getId(), employeeDTO.getUsername(), null);
  //  }
  //
  //  public Employee fromDTO(EmployeeNewDTO newDTO) {
  //    return new Employee(null, newDTO.getUsername(), newDTO.getPassword());
  //  }
}
