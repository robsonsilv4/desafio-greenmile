package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
  Usuario insert(Usuario usuario);

  Usuario find(Long id);

  List<Usuario> findAll();

  Usuario fromDTO(UsuarioDTO usuarioDTO);

  Usuario fromDTO(NovoUsuarioDTO novoDTO);

  Usuario update(Usuario usuario);

  void delete(Long id);
}
