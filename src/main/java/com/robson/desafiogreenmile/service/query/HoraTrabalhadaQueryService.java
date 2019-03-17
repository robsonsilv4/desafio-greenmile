package com.robson.desafiogreenmile.service.query;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import org.springframework.data.domain.Page;

public interface HoraTrabalhadaQueryService {

  HoraTrabalhada find(Long id);

  Page<HoraTrabalhada> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction);
}
