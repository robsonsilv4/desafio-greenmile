package com.robson.desafiogreenmile.resource.command;

import com.robson.desafiogreenmile.security.JWTUtil;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.security.UsuarioDetails;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
@Api(
    value = "Autenticação - Token",
    tags = "Autenticação - Token",
    description = "Gera um novo token para o usuário.")
public class AuthCommandResource {

  @Autowired private JWTUtil jwtUtil;

  @PostMapping(value = "/refresh-token")
  public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
    UsuarioDetails user = UserService.authenticated();
    String token = jwtUtil.generateToken(user.getUsername());
    response.addHeader("Authorization", "Bearer " + token);
    response.addHeader("access-control-expose-headers", "Authorization");
    return ResponseEntity.noContent().build();
  }
}
