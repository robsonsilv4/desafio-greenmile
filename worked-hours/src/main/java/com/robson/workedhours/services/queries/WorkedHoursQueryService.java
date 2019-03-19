package com.robson.workedhours.services.queries;

import com.robson.core.domains.WorkedHours;
import org.springframework.data.domain.Page;

public interface WorkedHoursQueryService {

  WorkedHours find(Long id);

  Page<WorkedHours> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);
}
