package com.robson.desafiogreenmile.services.queries.impls;

import com.robson.desafiogreenmile.domains.Employee;
import com.robson.desafiogreenmile.exceptions.ObjectNotFoundException;
import com.robson.desafiogreenmile.repositories.EmployeeRepository;
import com.robson.desafiogreenmile.services.queries.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

  @Autowired private EmployeeRepository employeeRepository;

  @Override
  public Employee find(Long id) {
    //  Implementação para garantir que o usuário só recupere ele mesmo.
    //  UserSecurityDetails user = UserService.authenticated();
    //    if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
    //      throw new AuthorizationException("Acesso negado!");
    //  }

    Optional<Employee> usuario = employeeRepository.findById(id);
    return usuario.orElseThrow(
        () ->
            new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Employee.class.getName()));
  }

  @Override
  @Cacheable(value = "usuarios")
  public Page<Employee> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return employeeRepository.findAll(pageRequest);
  }
}
