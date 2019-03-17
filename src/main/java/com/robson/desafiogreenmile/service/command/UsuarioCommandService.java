package com.robson.desafiogreenmile.service.command;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.command.NovoUsuarioCommandDTO;
import com.robson.desafiogreenmile.dto.command.UsuarioCommandDTO;

public interface UsuarioCommandService {
  Usuario insert(Usuario usuario);

  Usuario update(Usuario usuario);

  void delete(Long id);

  // Métodos auxiliares
  Usuario fromDTO(UsuarioCommandDTO usuarioDTO);

  Usuario fromDTO(NovoUsuarioCommandDTO novoDTO);
}
