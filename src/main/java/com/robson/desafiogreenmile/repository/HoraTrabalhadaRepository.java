package com.robson.desafiogreenmile.repository;

import com.robson.desafiogreenmile.domain.WorkedHours;
import com.robson.desafiogreenmile.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface HoraTrabalhadaRepository extends JpaRepository<WorkedHours, Long> {

  @Transactional(readOnly = true)
  Page<WorkedHours> findByUser(Employee employee, Pageable pageRequest);
}
