package com.robson.desafiogreenmile.resource.command;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.service.command.HoraTrabalhadaCommandService;
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
public class HoraTrabalhadaCommandResource {

  @Autowired private HoraTrabalhadaCommandService horaTrabalhadaCommand;

  @PostMapping
  public ResponseEntity<Void> insert(@Valid @RequestBody HoraTrabalhada horaTrabalhada) {
    horaTrabalhada = horaTrabalhadaCommand.insert(horaTrabalhada);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(horaTrabalhada.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }
}
