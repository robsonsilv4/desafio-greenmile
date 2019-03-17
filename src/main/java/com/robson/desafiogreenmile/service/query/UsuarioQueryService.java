package com.robson.desafiogreenmile.service.query;

import com.robson.desafiogreenmile.domain.Usuario;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

public interface UsuarioQueryService {
  Usuario find(Long id);

  Page<Usuario> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);
}
