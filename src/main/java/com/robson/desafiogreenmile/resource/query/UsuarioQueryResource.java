package com.robson.desafiogreenmile.resource.query;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.command.UsuarioCommandDTO;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioQueryResource {

  @Autowired private UsuarioQueryService usuarioService;
  //  @Autowired private ModelMapper modelMapper;

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> find(@PathVariable Long id) {
    Usuario usuario = usuarioService.find(id);
    return ResponseEntity.ok().body(usuario);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<List<UsuarioCommandDTO>> findAll() {
    List<Usuario> list = usuarioService.findAll();
    List<UsuarioCommandDTO> listDTO =
        list.stream().map(usuario -> new UsuarioCommandDTO(usuario)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }
}
