package com.robson.hours.resources.commands;

import com.robson.core.domains.Employee;
import com.robson.core.dtos.EmployeeDTO;
import com.robson.core.dtos.EmployeeNewDTO;
import com.robson.hours.services.commands.EmployeeCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/usuarios")
@Api(
    value = "Usuários - Comandos",
    tags = "Usuários - Comandos",
    description = "Cadastro, alteração e deleção de usuários.")
public class EmployeeCommandResource {
  @Autowired private EmployeeCommandService usuarioService;

  @PostMapping
  @ApiOperation(value = "Cadastra um novo usuário.")
  public ResponseEntity<Void> insert(@Valid @RequestBody EmployeeNewDTO employeeNewDTO) {
    Employee employee = usuarioService.fromDTO(employeeNewDTO);
    employee = usuarioService.insert(employee);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(employee.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  @ApiOperation(value = "Atualiza as informações de um usuário existente.")
  public ResponseEntity<Void> update(
      @Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
    Employee employee = usuarioService.fromDTO(employeeDTO);
    employee.setId(id);
    employee = usuarioService.update(employee);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  //  @PreAuthorize("hasAnyRole('ADMIN')")
  @ApiOperation(
      value =
          "Remove um usuário (Somente usuários com o perfil 'ADMIN' podem executar esta ação!).")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    usuarioService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
