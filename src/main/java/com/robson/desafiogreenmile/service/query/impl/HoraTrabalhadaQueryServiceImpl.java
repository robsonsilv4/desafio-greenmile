package com.robson.desafiogreenmile.service.query.impl;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.exception.ObjectNotFoundException;
import com.robson.desafiogreenmile.repository.HoraTrabalhadaRepository;
import com.robson.desafiogreenmile.service.query.HoraTrabalhadaQueryService;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
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
public class HoraTrabalhadaQueryServiceImpl implements HoraTrabalhadaQueryService {

  @Autowired private HoraTrabalhadaRepository horaTrabalhadaRepository;
  @Autowired private UsuarioQueryService usuarioService;

  //  @Cacheable
  public HoraTrabalhada find(Long id) {
    Optional<HoraTrabalhada> horaTrabalhada = horaTrabalhadaRepository.findById(id);
    return horaTrabalhada.orElseThrow(
        () ->
            new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + HoraTrabalhada.class.getName()));
  }

  @Cacheable
  public Page<HoraTrabalhada> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction) {
    // Implementação para garantir que o usuário recupere somente suas horas.
    //  UsuarioDetails user = UserService.authenticated();
    //    if (user == null) {
    //    throw new AuthorizationException("Acesso negado!");
    //  }
    // ...
    // Usuario usuario = usuarioService.find(user.getId());
    // ...
    // return horaTrabalhadaRepository.findByUsuario(usuario, pageRequest);

    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return horaTrabalhadaRepository.findAll(pageRequest);
  }
}
