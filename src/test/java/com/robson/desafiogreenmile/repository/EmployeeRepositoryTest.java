package com.robson.desafiogreenmile.repository;

import com.robson.desafiogreenmile.domain.Employee;
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

  @Autowired private UsuarioRepository usuarioRepository;
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void insereUsuario() {
    Employee employee = new Employee();

    employee.setId(null);
    employee.setName("Robson");
    employee.setEmail("robson@gmail.com");

    this.usuarioRepository.save(employee);

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

    this.usuarioRepository.save(employee);

    this.usuarioRepository.delete(employee);

    assertThat(usuarioRepository.findById(employee.getId())).isEmpty();
  }

  @Test
  public void atualizaInformacoes() {
    Employee employee = new Employee();

    employee.setId(null);
    employee.setName("Robson");
    employee.setEmail("robsonsilva@gmail.com");

    this.usuarioRepository.save(employee);

    employee.setName("Robson Silva");
    employee.setEmail("robsonsilva@greenmile.com");

    this.usuarioRepository.save(employee);

    Optional<Employee> usuarioAtualizado = usuarioRepository.findById(employee.getId());

    assertThat(usuarioAtualizado.get().getName()).isEqualTo("Robson Silva");
    assertThat(usuarioAtualizado.get().getEmail()).isEqualTo("robsonsilva@greenmile.com");
  }

  @Test
  public void emailNaoPodeSerNulo() {
    thrown.expect(DataIntegrityViolationException.class);
    this.usuarioRepository.save(new Employee());
  }

  @Test
  public void emailDeveSerUnico() {
    thrown.expect(DataIntegrityViolationException.class);

    Employee employee1 = new Employee();
    employee1.setEmail("robson@gmail.com");

    Employee employee2 = new Employee();
    employee2.setEmail("robson@gmail.com");

    this.usuarioRepository.saveAll(Arrays.asList(employee1, employee2));
  }
}
