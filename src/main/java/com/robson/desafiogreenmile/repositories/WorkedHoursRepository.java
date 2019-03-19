package com.robson.desafiogreenmile.repositories;

import com.robson.desafiogreenmile.domains.Employee;
import com.robson.desafiogreenmile.domains.WorkedHours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WorkedHoursRepository extends JpaRepository<WorkedHours, Long> {

  @Transactional(readOnly = true)
  Page<WorkedHours> findByEmployee(Employee employee, Pageable pageRequest);
}
