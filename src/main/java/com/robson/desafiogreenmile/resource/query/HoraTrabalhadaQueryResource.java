package com.robson.desafiogreenmile.resource.query;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.service.query.HoraTrabalhadaQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/horas")
@Api(
    value = "Horas Trabalhadas - Consultas",
    tags = "Horas Trabalhadas - Consultas",
    description = "Consultas de horas trabalhadas.")
public class HoraTrabalhadaQueryResource {

  @Autowired private HoraTrabalhadaQueryService horaTrabalhadaQuery;

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Busca por um determinado registro de horas trabalhadas.")
  public ResponseEntity<HoraTrabalhada> find(@PathVariable Long id) {
    HoraTrabalhada horaTrabalhada = horaTrabalhadaQuery.find(id);
    return ResponseEntity.ok().body(horaTrabalhada);
  }

  @GetMapping
  @ApiOperation(value = "Retorna todos os registros de horas trabalhadas.")
  public ResponseEntity<Page<HoraTrabalhada>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "data") String orderBy,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
    Page<HoraTrabalhada> list = horaTrabalhadaQuery.findAll(page, linesPerPage, orderBy, direction);
    return ResponseEntity.ok().body(list);
  }
}
