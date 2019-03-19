package com.robson.hours.services.commands;

import com.robson.core.domains.Employee;
import com.robson.hours.services.queries.EmployeeQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
public class EmployeeCommandServiceTest {

  @Autowired private EmployeeCommandService employeeCommandService;
  @Autowired private EmployeeQueryService employeeQueryService;

  @Test
  public void testInsertEmployee() {
    Employee employee = employeeCommandService.insert(new Employee(null, "samuel", "greenmile"));

    assertThat(employee).isNotNull();
    assertThat(employee.getId()).isNotNull();
    assertThat(employee.getUsername()).isEqualTo("samuel");
  }

  @Test
  public void testUpdateEmployee() {
    Employee employee = employeeCommandService.insert(new Employee(null, "robson", "desafio"));
    assertThat(employee.getId()).isNotNull();
    assertThat(employee.getUsername()).isEqualTo("robson");

    employee.setUsername("robsonsilva");
    employeeCommandService.update(employee);
    assertThat(employee.getUsername()).isEqualTo("robsonsilva");
  }

  @Test
  public void testDeleteEmployee() {
    Employee employee = employeeCommandService.insert(new Employee(null, "gabriel", "czar"));
    assertThat(employee.getId()).isNotNull();

    employeeCommandService.delete(employee.getId());
    assertThat(employeeQueryService.findByUsername("gabriel")).isNull();
  }
}
