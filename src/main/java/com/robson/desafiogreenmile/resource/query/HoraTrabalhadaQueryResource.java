package com.robson.desafiogreenmile.resource.query;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.service.query.HoraTrabalhadaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/horas")
public class HoraTrabalhadaQueryResource {

  @Autowired private HoraTrabalhadaQueryService horaTrabalhadaQuery;

  @GetMapping(value = "/{id}")
  public ResponseEntity<HoraTrabalhada> find(@PathVariable Long id) {
    HoraTrabalhada horaTrabalhada = horaTrabalhadaQuery.find(id);
    return ResponseEntity.ok().body(horaTrabalhada);
  }

  @GetMapping
  public ResponseEntity<Page<HoraTrabalhada>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "data") String orderBy,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
    Page<HoraTrabalhada> list = horaTrabalhadaQuery.findAll(page, linesPerPage, orderBy, direction);
    return ResponseEntity.ok().body(list);
  }
}
