package com.robson.desafiogreenmile.service.command;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;

public interface UsuarioCommandService {
  Usuario insert(Usuario usuario);

  Usuario update(Usuario usuario);

  void delete(Long id);

  // Métodos auxiliares
  Usuario fromDTO(UsuarioDTO usuarioDTO);

  Usuario fromDTO(NovoUsuarioDTO novoDTO);
}
