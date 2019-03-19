package com.robson.desafiogreenmile.service.command.impl;

import com.robson.desafiogreenmile.domain.WorkedHours;
import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.repository.HoraTrabalhadaRepository;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.security.UsuarioDetails;
import com.robson.desafiogreenmile.service.command.HoraTrabalhadaCommandService;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional
public class HoraTrabalhadaCommandServiceImpl implements HoraTrabalhadaCommandService {

  @Autowired private HoraTrabalhadaRepository horaTrabalhadaRepository;
  @Autowired private UsuarioQueryService usuarioService;

  //  @CachePut
  @Override
  public WorkedHours insert(WorkedHours workedHours) {
    workedHours.setId(null);
    workedHours.setWorkedHours(
        Duration.between(workedHours.getInitialTime(), workedHours.getFinalTime()).toHours());

    UsuarioDetails user = UserService.authenticated();
    Employee employee = usuarioService.find(user.getId());
    workedHours.setEmployee(employee);

    return horaTrabalhadaRepository.save(workedHours);
  }
}
