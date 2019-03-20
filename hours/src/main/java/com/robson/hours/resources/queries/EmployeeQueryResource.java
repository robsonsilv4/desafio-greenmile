package com.robson.hours.resources.queries;

import com.robson.core.domains.Employee;
import com.robson.core.dtos.EmployeeDTO;
import com.robson.hours.services.queries.EmployeeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
@Api(
    value = "Usuários - Consultas",
    tags = "Usuários - Consultas",
    description = "Consultas de usuários.")
public class EmployeeQueryResource {

  @Autowired private EmployeeQueryService usuarioService;

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Retorna um determinado usuário.")
  public ResponseEntity<?> find(@PathVariable Long id) {
    Employee employee = usuarioService.find(id);
    return ResponseEntity.ok().body(employee);
  }

//  @GetMapping
//  @ApiOperation(value = "Retorna uma lista com todos os usuários.")
//  public ResponseEntity<Page<EmployeeDTO>> findAll(
//      @RequestParam(value = "page", defaultValue = "0") Integer page,
//      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
//      @RequestParam(value = "orderBy", defaultValue = "username") String orderBy,
//      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
//    Page<Employee> list = usuarioService.findAll(page, linesPerPage, orderBy, direction);
//    Page<EmployeeDTO> listDTO = list.map(obj -> new EmployeeDTO(obj));
//    return ResponseEntity.ok().body(listDTO);
//  }
}
