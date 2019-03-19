package com.robson.desafiogreenmile.service.query.impl;

import com.robson.desafiogreenmile.domain.WorkedHours;
import com.robson.desafiogreenmile.exception.ObjectNotFoundException;
import com.robson.desafiogreenmile.repository.WorkedHoursRepository;
import com.robson.desafiogreenmile.service.query.WorkedHoursQueryService;
import com.robson.desafiogreenmile.service.query.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@CacheConfig(cacheNames = {"horas"})
public class WorkedHoursQueryServiceImpl implements WorkedHoursQueryService {

  @Autowired private WorkedHoursRepository workedHoursRepository;
  @Autowired private EmployeeQueryService usuarioService;

  //  @Cacheable
  public WorkedHours find(Long id) {
    Optional<WorkedHours> horaTrabalhada = workedHoursRepository.findById(id);
    return horaTrabalhada.orElseThrow(
        () ->
            new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + WorkedHours.class.getName()));
  }

  @Cacheable
  public Page<WorkedHours> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction) {
    // Implementação para garantir que o usuário recupere somente suas horas.
    //  UserSecurityDetails user = UserService.authenticated();
    //    if (user == null) {
    //    throw new AuthorizationException("Acesso negado!");
    //  }
    // ...
    // Employee employee = usuarioService.find(user.getId());
    // ...
    // return workedHoursRepository.findByUsuario(employee, pageRequest);

    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return workedHoursRepository.findAll(pageRequest);
  }
}
