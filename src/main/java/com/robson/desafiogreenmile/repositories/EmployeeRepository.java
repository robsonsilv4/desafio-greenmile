package com.robson.desafiogreenmile.repositories;

import com.robson.desafiogreenmile.domains.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Transactional(readOnly = true)
  Employee findByEmail(String email);
}
