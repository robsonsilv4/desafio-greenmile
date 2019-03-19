package com.robson.desafiogreenmile.services.queries;

import com.robson.desafiogreenmile.domains.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeQueryService {
  Employee find(Long id);

  Page<Employee> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);
}
