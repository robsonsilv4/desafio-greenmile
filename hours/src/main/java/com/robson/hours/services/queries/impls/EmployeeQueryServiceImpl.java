package com.robson.hours.services.queries.impls;

import com.robson.core.domains.Employee;
import com.robson.core.repositories.EmployeeRepository;
import com.robson.hours.exceptions.ObjectNotFoundException;
import com.robson.hours.services.queries.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"employees"})
// @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

  @Autowired private EmployeeRepository employeeRepository;

  @Override
  public Employee find(Long id) {
    Optional<Employee> usuario = employeeRepository.findById(id);
    return usuario.orElseThrow(
        () -> new ObjectNotFoundException("Funcionário não encontrado! Id: " + id));
  }

  @Override
  public Employee findByUsername(String username) {
    return employeeRepository.findByUsername(username);
  }

  @Override
  @Cacheable
  public Page<Employee> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return employeeRepository.findAll(pageRequest);
  }
}
