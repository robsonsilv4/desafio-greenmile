package com.robson.desafiogreenmile.repository;

import com.robson.desafiogreenmile.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  @Transactional(readOnly = true)
  Usuario findByEmail(String email);
}
