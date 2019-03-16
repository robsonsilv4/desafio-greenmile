package com.robson.desafiogreenmile.repository;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface HoraTrabalhadaRepository extends JpaRepository<HoraTrabalhada, Long> {

  @Transactional(readOnly = true)
  Page<HoraTrabalhada> findByUsuario(Usuario usuario, Pageable pageRequest);
}
