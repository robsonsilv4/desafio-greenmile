package com.robson.desafiogreenmile.service.query;

import com.robson.desafiogreenmile.domain.Usuario;

import java.util.List;

public interface UsuarioQueryService {
  Usuario find(Long id);

  List<Usuario> findAll();
}
