package com.robson.desafiogreenmile.service.command.impl;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.domain.WorkedHours;
import com.robson.desafiogreenmile.repository.WorkedHoursRepository;
import com.robson.desafiogreenmile.security.UserSecurityDetails;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.service.command.WorkedHoursCommandService;
import com.robson.desafiogreenmile.service.query.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional
public class WorkedHoursCommandServiceImpl implements WorkedHoursCommandService {

  @Autowired private WorkedHoursRepository workedHoursRepository;
  @Autowired private EmployeeQueryService employeeService;

  @Override
  public WorkedHours insert(WorkedHours workedHours) {
    workedHours.setId(null);
    workedHours.setWorkedHours(
        Duration.between(workedHours.getInitialTime(), workedHours.getFinalTime()).toHours());

    UserSecurityDetails user = UserService.authenticated();
    Employee employee = employeeService.find(user.getId());
    workedHours.setEmployee(employee);

    return workedHoursRepository.save(workedHours);
  }
}
