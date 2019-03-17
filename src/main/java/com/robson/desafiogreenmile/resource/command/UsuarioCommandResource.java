package com.robson.desafiogreenmile.resource.command;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.command.NovoUsuarioCommandDTO;
import com.robson.desafiogreenmile.dto.command.UsuarioCommandDTO;
import com.robson.desafiogreenmile.service.command.UsuarioCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioCommandResource {
  @Autowired private UsuarioCommandService usuarioService;

  @PostMapping
  public ResponseEntity<Void> insert(@Valid @RequestBody NovoUsuarioCommandDTO novoUsuarioDTO) {
    Usuario usuario = usuarioService.fromDTO(novoUsuarioDTO);
    usuario = usuarioService.insert(usuario);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(usuario.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(
          @Valid @RequestBody UsuarioCommandDTO usuarioDTO, @PathVariable Long id) {
    Usuario usuario = usuarioService.fromDTO(usuarioDTO);
    usuario.setId(id);
    usuario = usuarioService.update(usuario);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    usuarioService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
