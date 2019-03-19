package com.robson.desafiogreenmile.resource.command;

import com.robson.desafiogreenmile.domain.Employee;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;
import com.robson.desafiogreenmile.service.command.UsuarioCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class UsuarioCommandResource {
  @Autowired private UsuarioCommandService usuarioService;

  @PostMapping
  @ApiOperation(value = "Cadastra um novo usuário.")
  public ResponseEntity<Void> insert(@Valid @RequestBody NovoUsuarioDTO novoUsuarioDTO) {
    Employee employee = usuarioService.fromDTO(novoUsuarioDTO);
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
      @Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
    Employee employee = usuarioService.fromDTO(usuarioDTO);
    employee.setId(id);
    employee = usuarioService.update(employee);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  @ApiOperation(
      value =
          "Remove um usuário (Somente usuários com o perfil 'ADMIN' podem executar esta ação!).")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    usuarioService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
