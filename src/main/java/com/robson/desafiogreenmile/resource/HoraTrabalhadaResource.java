package com.robson.desafiogreenmile.resource;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.service.HoraTrabalhadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/horas-trabalhadas")
public class HoraTrabalhadaResource {

    @Autowired
    private HoraTrabalhadaService horaTrabalhadaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<HoraTrabalhada>> find(@PathVariable Long id) {
        Optional<HoraTrabalhada> horaTrabalhada = horaTrabalhadaService.find(id);
        return ResponseEntity.ok().body(horaTrabalhada);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody HoraTrabalhada horaTrabalhada) {
        horaTrabalhada = horaTrabalhadaService.insert(horaTrabalhada);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(horaTrabalhada.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
