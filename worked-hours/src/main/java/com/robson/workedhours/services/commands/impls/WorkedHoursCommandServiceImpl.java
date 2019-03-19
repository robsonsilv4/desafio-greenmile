package com.robson.workedhours.services.commands.impls;

import com.robson.core.domains.Employee;
import com.robson.core.domains.WorkedHours;
import com.robson.core.repositories.WorkedHoursRepository;
import com.robson.workedhours.security.UserSecurityDetails;
import com.robson.workedhours.security.UserService;
import com.robson.workedhours.services.commands.WorkedHoursCommandService;
import com.robson.workedhours.services.queries.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    if (workedHours.getInitialTime().isBefore(workedHours.getFinalTime())) {
      throw new DataIntegrityViolationException("A data final deve ser posterior a data inicial!");
    }

    workedHours.setWorkedHours(
        Duration.between(workedHours.getInitialTime(), workedHours.getFinalTime()).toHours());

    UserSecurityDetails user = UserService.authenticated();
    Employee employee = employeeService.find(user.getId());
    workedHours.setEmployee(employee);

    return workedHoursRepository.save(workedHours);
  }
}
