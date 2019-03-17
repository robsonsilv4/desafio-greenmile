package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.command.NovoUsuarioCommandDTO;
import com.robson.desafiogreenmile.dto.command.UsuarioCommandDTO;

public interface UsuarioService {




  Usuario fromDTO(UsuarioCommandDTO usuarioDTO);

  Usuario fromDTO(NovoUsuarioCommandDTO novoDTO);




}
