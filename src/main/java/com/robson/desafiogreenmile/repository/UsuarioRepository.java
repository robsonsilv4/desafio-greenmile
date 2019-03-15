package com.robson.desafiogreenmile.repository;

import com.robson.desafiogreenmile.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
