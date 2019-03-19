package com.robson.desafiogreenmile.services.queries.impls;

import com.robson.desafiogreenmile.domains.WorkedHours;
import com.robson.desafiogreenmile.exceptions.ObjectNotFoundException;
import com.robson.desafiogreenmile.repositories.WorkedHoursRepository;
import com.robson.desafiogreenmile.services.queries.EmployeeQueryService;
import com.robson.desafiogreenmile.services.queries.WorkedHoursQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames = {"hours"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkedHoursQueryServiceImpl implements WorkedHoursQueryService {

  private final WorkedHoursRepository workedHoursRepository;
  private final EmployeeQueryService employeeService;

  public WorkedHours find(Long id) {
    Optional<WorkedHours> workedHours = workedHoursRepository.findById(id);
    return workedHours.orElseThrow(
        () ->
            new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + WorkedHours.class.getName()));
  }

  @Cacheable
  public Page<WorkedHours> findAll(
      Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return workedHoursRepository.findAll(pageRequest);
  }
}
