package com.robson.desafiogreenmile.resource;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;
import com.robson.desafiogreenmile.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

  @Autowired private UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<Void> insert(@Valid @RequestBody NovoUsuarioDTO novoUsuarioDTO) {
    Usuario usuario = usuarioService.fromDTO(novoUsuarioDTO);
    usuario = usuarioService.insert(usuario);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(usuario.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> find(@PathVariable Long id) {
    Usuario usuario = usuarioService.find(id);
    return ResponseEntity.ok().body(usuario);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(
      @Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
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

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<List<UsuarioDTO>> findAll() {
    List<Usuario> list = usuarioService.findAll();
    List<UsuarioDTO> listDTO =
        list.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }
}
