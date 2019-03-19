package com.robson.desafiogreenmile.service.command.impl;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.service.command.UsuarioCommandService;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioCommandServiceImpl implements UsuarioCommandService {

  @Autowired private UsuarioRepository usuarioRepository;
  @Autowired private UsuarioQueryService usuarioQuery;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Employee insert(Employee employee) {
    employee.setId(null);
    employee = usuarioRepository.save(employee);
    return employee;
  }

  @Override
  public Employee update(Employee employee) {
    Employee novoEmployee = usuarioQuery.find(employee.getId());
    updateData(novoEmployee, employee);
    return usuarioRepository.save(novoEmployee);
  }

  @Override
  public void delete(Long id) {
    usuarioQuery.find(id);
    usuarioRepository.deleteById(id);
  }

  // Métodos auxiliares
  public void updateData(Employee novoEmployee, Employee employee) {
    novoEmployee.setName(employee.getName());
    novoEmployee.setEmail(employee.getEmail());
  }

  public Employee fromDTO(UsuarioDTO usuarioDTO) {
    return new Employee(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), null);
  }

  public Employee fromDTO(NovoUsuarioDTO novoDTO) {
    return new Employee(
        null,
        novoDTO.getNome(),
        novoDTO.getEmail(),
        bCryptPasswordEncoder.encode(novoDTO.getSenha()));
  }
}
