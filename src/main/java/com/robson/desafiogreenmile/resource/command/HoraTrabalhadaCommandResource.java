package com.robson.desafiogreenmile.resource.command;

import com.robson.desafiogreenmile.domain.WorkedHours;
import com.robson.desafiogreenmile.service.command.HoraTrabalhadaCommandService;
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
public class HoraTrabalhadaCommandResource {

  @Autowired private HoraTrabalhadaCommandService horaTrabalhadaCommand;

  @PostMapping(
      consumes = "application/json",
      headers = "content-type=application/x-www-form-urlencoded")
  @ApiOperation(value = "Cadastra um novo registro de horas trabalhadas.")
  public ResponseEntity<Void> insert(@Valid @RequestBody WorkedHours workedHours) {
    workedHours = horaTrabalhadaCommand.insert(workedHours);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(workedHours.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }
}
