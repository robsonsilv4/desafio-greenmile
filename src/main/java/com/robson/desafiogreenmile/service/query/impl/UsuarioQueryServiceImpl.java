package com.robson.desafiogreenmile.service.query.impl;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.domain.enumeration.Perfil;
import com.robson.desafiogreenmile.exception.AuthorizationException;
import com.robson.desafiogreenmile.exception.ObjectNotFoundException;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.security.UsuarioDetails;
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
  public Usuario find(Long id) {
    UsuarioDetails user = UserService.authenticated();
    if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
      throw new AuthorizationException("Acesso negado!");
    }

    Optional<Usuario> usuario = usuarioRepository.findById(id);
    return usuario.orElseThrow(
        () ->
            new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Usuario.class.getName()));
  }

  @Override
  @Cacheable(value = "usuarios")
  public Page<Usuario> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return usuarioRepository.findAll(pageRequest);
  }
}
