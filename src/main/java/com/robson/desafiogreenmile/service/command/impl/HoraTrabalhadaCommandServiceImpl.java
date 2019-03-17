package com.robson.desafiogreenmile.service.command.impl;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.repository.HoraTrabalhadaRepository;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.security.UsuarioDetails;
import com.robson.desafiogreenmile.service.command.HoraTrabalhadaCommandService;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HoraTrabalhadaCommandServiceImpl implements HoraTrabalhadaCommandService {

  @Autowired private HoraTrabalhadaRepository horaTrabalhadaRepository;
  @Autowired private UsuarioQueryService usuarioQuery;

  @Override
  public HoraTrabalhada insert(HoraTrabalhada horaTrabalhada) {
    horaTrabalhada.setId(null);

    UsuarioDetails user = UserService.authenticated();
    Usuario usuario = usuarioQuery.find(user.getId());
    horaTrabalhada.setUsuario(usuario);

    // Define a quantidade padrão, se o atributo for nulo.
    if (horaTrabalhada.getQuantidade() == null) {
      horaTrabalhada.setQuantidade(0);
    }

    return horaTrabalhadaRepository.save(horaTrabalhada);
  }
}
