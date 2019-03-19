package com.robson.desafiogreenmile.resources.commands;

import com.robson.desafiogreenmile.domains.WorkedHours;
import com.robson.desafiogreenmile.services.commands.WorkedHoursCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/horas")
@Api(
    value = "Horas Trabalhadas - Comandos",
    tags = "Horas Trabalhadas - Comandos",
    description = "Cadastro de horas trabalhadas.")
public class WorkedHoursCommandResource {

  @Autowired private WorkedHoursCommandService workedHoursService;

  @PostMapping(
      consumes = "application/json",
      headers = "content-type=application/x-www-form-urlencoded")
  @ApiOperation(value = "Cadastra um novo registro de horas trabalhadas.")
  public ResponseEntity<Void> insert(@Valid @RequestBody WorkedHours workedHours) {
    workedHours = workedHoursService.insert(workedHours);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(workedHours.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }
}
