package com.robson.hours.resources.commands;

import com.robson.core.domains.Employee;
import com.robson.core.dtos.EmployeeDTO;
import com.robson.core.dtos.EmployeeNewDTO;
import com.robson.hours.services.commands.EmployeeCommandService;
import com.robson.hours.services.commands.EmployeeCommandServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Usuários - Comandos", description = "Cadastro, alteração e deleção de usuários.")
public class EmployeeCommandResource {

  @Autowired private EmployeeCommandServiceImpl employeeService;

  @PostMapping
  public Employee insert(@Valid @RequestBody Employee employee) {
    return employeeService.insert(employee);
  }
}
