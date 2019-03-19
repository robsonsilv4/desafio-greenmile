package com.robson.desafiogreenmile.service.query;

import com.robson.desafiogreenmile.domain.WorkedHours;
import org.springframework.data.domain.Page;

public interface HoraTrabalhadaQueryService {

  WorkedHours find(Long id);

  Page<WorkedHours> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction);
}
