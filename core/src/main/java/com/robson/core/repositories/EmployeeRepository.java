package com.robson.core.repositories;

import com.robson.core.domains.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Transactional(readOnly = true)
  Employee findByUsername(String username);
}
