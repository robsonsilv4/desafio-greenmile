package com.robson.auth.resources.commands;

import com.robson.auth.filters.utils.JWTUtil;
import com.robson.auth.security.UserSecurityDetails;
import com.robson.auth.security.UserService;
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
public class AuthenticationCommandResource {

  @Autowired private JWTUtil jwtUtil;

  @PostMapping(value = "/refresh-token")
  public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
    UserSecurityDetails user = UserService.authenticated();
    String token = jwtUtil.generateToken(user.getUsername());
    response.addHeader("Authorization", "Bearer " + token);
    response.addHeader("access-control-expose-headers", "Authorization");
    return ResponseEntity.noContent().build();
  }
}
