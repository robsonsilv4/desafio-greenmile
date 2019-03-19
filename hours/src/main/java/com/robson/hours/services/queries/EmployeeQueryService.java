package com.robson.hours.services.queries;

import com.robson.core.domains.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeQueryService {
  Employee find(Long id);

  Employee findByUsername(String username);

  Page<Employee> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);
}
