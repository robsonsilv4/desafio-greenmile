package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import org.springframework.data.domain.Page;

public interface HoraTrabalhadaService {
  HoraTrabalhada insert(HoraTrabalhada horaTrabalhada);

  HoraTrabalhada find(Long id);

  Page<HoraTrabalhada> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction);
}
