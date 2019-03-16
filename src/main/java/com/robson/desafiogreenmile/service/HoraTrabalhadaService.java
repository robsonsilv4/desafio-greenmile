package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.exception.AuthorizationException;
import com.robson.desafiogreenmile.exception.ObjectNotFoundException;
import com.robson.desafiogreenmile.repository.HoraTrabalhadaRepository;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.security.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HoraTrabalhadaService {

  @Autowired private HoraTrabalhadaRepository horaTrabalhadaRepository;
  @Autowired private UsuarioService usuarioService;

  public HoraTrabalhada insert(HoraTrabalhada horaTrabalhada) {
    horaTrabalhada.setId(null);
    horaTrabalhada = horaTrabalhadaRepository.save(horaTrabalhada);
    return horaTrabalhada;
  }

  public HoraTrabalhada find(Long id) {
    Optional<HoraTrabalhada> horaTrabalhada = horaTrabalhadaRepository.findById(id);
    return horaTrabalhada.orElseThrow(
        () ->
            new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + HoraTrabalhada.class.getName()));
  }

  public Page<HoraTrabalhada> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction) {
    UsuarioDetails user = UserService.authenticated();

    if (user == null) {
      throw new AuthorizationException("Acesso negado!");
    }

    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    Usuario usuario = usuarioService.buscar(user.getId());

    return horaTrabalhadaRepository.findByUsuario(usuario, pageRequest);
  }
}
