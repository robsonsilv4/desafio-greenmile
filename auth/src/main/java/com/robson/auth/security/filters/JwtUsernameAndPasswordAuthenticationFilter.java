package com.robson.auth.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import com.robson.core.configuration.JwtConfiguration;
import com.robson.core.domains.Employee;
import com.robson.token.creator.TokenCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Collections.emptyList;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUsernameAndPasswordAuthenticationFilter
    extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtConfiguration jwtConfiguration;
  private final TokenCreator tokenCreator;

  @Override
  @SneakyThrows
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) {
    log.info("Tentando realizar a autenticação...");

    Employee employee = new ObjectMapper().readValue(request.getInputStream(), Employee.class);

    if (employee == null)
      throw new UsernameNotFoundException(
          "Não foi possível encontrar o nome de usuário ou a senha!");

    log.info(
        "Criando um objeto de autenticação para o usuário '{}' e chamando 'UserDetailServiceImpl loadUserByUsername'...",
        employee.getUsername());

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(
            employee.getUsername(), employee.getPassword(), emptyList());
    usernamePasswordAuthenticationToken.setDetails(employee);

    return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  }

  @Override
  @SneakyThrows
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication auth)
      throws IOException, ServletException {
    log.info("O usuário '{}', generating JWE token", auth.getName());

    SignedJWT signedJWT = tokenCreator.createSignedJWT(auth);

    String encryptedToken = tokenCreator.encryptToken(signedJWT);

    log.info("Token generated successfully, adding it to the response header");

    response.addHeader(
        "Access-Control-Expose-Headers", "XSRF-TOKEN, " + jwtConfiguration.getHeader().getName());

    response.addHeader(
        jwtConfiguration.getHeader().getName(),
        jwtConfiguration.getHeader().getPrefix() + encryptedToken);
  }
}
