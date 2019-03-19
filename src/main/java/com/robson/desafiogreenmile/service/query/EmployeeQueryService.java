package com.robson.desafiogreenmile.service.query;

import com.robson.desafiogreenmile.domain.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeQueryService {
  Employee find(Long id);

  Page<Employee> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);
}
