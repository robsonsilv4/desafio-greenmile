package com.robson.desafiogreenmile.resources.queries;

import com.robson.desafiogreenmile.domains.WorkedHours;
import com.robson.desafiogreenmile.services.queries.WorkedHoursQueryService;
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
public class WorkedHoursQueryResource {

  @Autowired private WorkedHoursQueryService workedHoursService;

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Busca por um determinado registro de horas trabalhadas.")
  public ResponseEntity<WorkedHours> find(@PathVariable Long id) {
    WorkedHours workedHours = workedHoursService.find(id);
    return ResponseEntity.ok().body(workedHours);
  }

  @GetMapping
  @ApiOperation(value = "Busca todos os registros de horas trabalhadas.")
  public ResponseEntity<Page<WorkedHours>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "25") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "date") String orderBy,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
    Page<WorkedHours> list = workedHoursService.findAll(page, linesPerPage, orderBy, direction);
    return ResponseEntity.ok().body(list);
  }
}
