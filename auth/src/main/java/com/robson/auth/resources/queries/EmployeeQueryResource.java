package com.robson.auth.resources.queries;

import com.robson.core.domains.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("funcionario")
public class EmployeeQueryResource {

  @GetMapping(path = "detalhes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Employee> getUserInfo(Principal principal) {
    Employee employee = (Employee) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }
}
