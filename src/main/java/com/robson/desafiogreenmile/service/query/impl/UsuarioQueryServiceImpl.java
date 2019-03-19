package com.robson.desafiogreenmile.service.query.impl;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.exception.ObjectNotFoundException;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioQueryServiceImpl implements UsuarioQueryService {

  @Autowired private UsuarioRepository usuarioRepository;

  @Override
  public Employee find(Long id) {
    //  Implementação para garantir que o usuário só recupere ele mesmo.
    //  UsuarioDetails user = UserService.authenticated();
    //    if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
    //      throw new AuthorizationException("Acesso negado!");
    //  }

    Optional<Employee> usuario = usuarioRepository.findById(id);
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
    return usuarioRepository.findAll(pageRequest);
  }
}
