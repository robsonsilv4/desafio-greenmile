package com.robson.desafiogreenmile.resource;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.service.HoraTrabalhadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/horas-trabalhadas")
public class HoraTrabalhadaResource {

  @Autowired private HoraTrabalhadaService horaTrabalhadaService;

  @PostMapping
  public ResponseEntity<Void> insert(@Valid @RequestBody HoraTrabalhada horaTrabalhada) {
    horaTrabalhada = horaTrabalhadaService.insert(horaTrabalhada);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(horaTrabalhada.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<HoraTrabalhada> find(@PathVariable Long id) {
    HoraTrabalhada horaTrabalhada = horaTrabalhadaService.find(id);
    return ResponseEntity.ok().body(horaTrabalhada);
  }

  @GetMapping
  public ResponseEntity<Page<HoraTrabalhada>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "data") String orderBy,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
    Page<HoraTrabalhada> list =
        horaTrabalhadaService.findAll(page, linesPerPage, orderBy, direction);
    return ResponseEntity.ok().body(list);
  }
}
