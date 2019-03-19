package com.robson.desafiogreenmile.services.queries;

import com.robson.desafiogreenmile.domains.WorkedHours;
import org.springframework.data.domain.Page;

public interface WorkedHoursQueryService {

  WorkedHours find(Long id);

  Page<WorkedHours> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction);
}
