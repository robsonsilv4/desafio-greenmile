package com.robson.hours.services.commands.impls;

import com.robson.core.domains.WorkedHours;
import com.robson.core.repositories.WorkedHoursRepository;
import com.robson.hours.services.commands.HoursCommandService;
import com.robson.hours.services.queries.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional
public class HoursCommandServiceImpl implements HoursCommandService {

  @Autowired private WorkedHoursRepository workedHoursRepository;
  @Autowired private EmployeeQueryService employeeService;

  @Override
  public WorkedHours insert(WorkedHours workedHours) {
    workedHours.setId(null);

    if (workedHours.getInitialTime().isBefore(workedHours.getFinalTime())) {
      throw new DataIntegrityViolationException("A data final deve ser posterior a data inicial!");
    }

    workedHours.setWorkedHours(
        Duration.between(workedHours.getInitialTime(), workedHours.getFinalTime()).toHours());

    // TODO: 19/03/19 
    //    UserSecurityDetails user = UserService.authenticated();
    //    Employee employee = employeeService.find(user.getId());
    //    workedHours.setEmployee(employee);

    return workedHoursRepository.save(workedHours);
  }
}
