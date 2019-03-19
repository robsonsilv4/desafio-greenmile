package com.robson.desafiogreenmile.service.command;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;

public interface UsuarioCommandService {
  Employee insert(Employee employee);

  Employee update(Employee employee);

  void delete(Long id);

  // Métodos auxiliares
  Employee fromDTO(UsuarioDTO usuarioDTO);

  Employee fromDTO(NovoUsuarioDTO novoDTO);
}
