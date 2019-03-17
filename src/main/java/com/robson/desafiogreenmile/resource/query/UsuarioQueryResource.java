package com.robson.desafiogreenmile.resource.query;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.command.UsuarioCommandDTO;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioQueryResource {

  @Autowired private UsuarioQueryService usuarioService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> find(@PathVariable Long id) {
    Usuario usuario = usuarioService.find(id);
    return ResponseEntity.ok().body(usuario);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Page<UsuarioCommandDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
    Page<Usuario> list = usuarioService.findAll(page, linesPerPage, orderBy, direction);
    Page<UsuarioCommandDTO> listDTO = list.map(obj -> new UsuarioCommandDTO(obj));
    return ResponseEntity.ok().body(listDTO);
  }
}
