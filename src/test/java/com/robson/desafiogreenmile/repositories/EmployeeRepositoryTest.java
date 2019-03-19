package com.robson.desafiogreenmile.repositories;

import com.robson.desafiogreenmile.domains.Employee;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

  @Autowired private EmployeeRepository employeeRepository;
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void insereUsuario() {
    Employee employee = new Employee();

    employee.setId(null);
    employee.setName("Robson");
    employee.setEmail("robson@gmail.com");

    this.employeeRepository.save(employee);

    assertThat(employee.getId()).isNotNull();
    assertThat(employee.getName()).isEqualTo("Robson");
    assertThat(employee.getEmail()).isEqualTo("robson@gmail.com");
  }

  @Test
  public void removeUsuario() {
    Employee employee = new Employee();

    employee.setId(null);
    employee.setName("Samuel");
    employee.setEmail("samuel@greenmile.com");

    this.employeeRepository.save(employee);

    this.employeeRepository.delete(employee);

    assertThat(employeeRepository.findById(employee.getId())).isEmpty();
  }

  @Test
  public void atualizaInformacoes() {
    Employee employee = new Employee();

    employee.setId(null);
    employee.setName("Robson");
    employee.setEmail("robsonsilva@gmail.com");

    this.employeeRepository.save(employee);

    employee.setName("Robson Silva");
    employee.setEmail("robsonsilva@greenmile.com");

    this.employeeRepository.save(employee);

    Optional<Employee> usuarioAtualizado = employeeRepository.findById(employee.getId());

    assertThat(usuarioAtualizado.get().getName()).isEqualTo("Robson Silva");
    assertThat(usuarioAtualizado.get().getEmail()).isEqualTo("robsonsilva@greenmile.com");
  }

  @Test
  public void emailNaoPodeSerNulo() {
    thrown.expect(DataIntegrityViolationException.class);
    this.employeeRepository.save(new Employee());
  }

  @Test
  public void emailDeveSerUnico() {
    thrown.expect(DataIntegrityViolationException.class);

    Employee employee1 = new Employee();
    employee1.setEmail("robson@gmail.com");

    Employee employee2 = new Employee();
    employee2.setEmail("robson@gmail.com");

    this.employeeRepository.saveAll(Arrays.asList(employee1, employee2));
  }
}
