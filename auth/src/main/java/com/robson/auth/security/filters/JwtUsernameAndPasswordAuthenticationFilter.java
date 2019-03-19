package com.robson.auth.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.robson.core.configuration.JwtConfiguration;
import com.robson.core.domains.Employee;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUsernameAndPasswordAuthenticationFilter
    extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtConfiguration jwtConfiguration;

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

    SignedJWT signedJWT = createSignedJWT(auth);

    String encryptedToken = encryptToken(signedJWT);

    log.info("Token generated successfully, adding it to the response header");

    response.addHeader(
        "Access-Control-Expose-Headers", "XSRF-TOKEN, " + jwtConfiguration.getHeader().getName());

    response.addHeader(
        jwtConfiguration.getHeader().getName(),
        jwtConfiguration.getHeader().getPrefix() + encryptedToken);
  }

  @SneakyThrows
  private SignedJWT createSignedJWT(Authentication auth) {
    log.info("Starting to create the signed JWT");

    Employee applicationUser = (Employee) auth.getPrincipal();

    JWTClaimsSet jwtClaimSet = createJWTClaimSet(auth, applicationUser);

    KeyPair rsaKeys = generateKeyPair();

    log.info("Building JWK from the RSA Keys");

    JWK jwk =
        new RSAKey.Builder((RSAPublicKey) rsaKeys.getPublic())
            .keyID(UUID.randomUUID().toString())
            .build();

    SignedJWT signedJWT =
        new SignedJWT(
            new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).type(JOSEObjectType.JWT).build(),
            jwtClaimSet);

    log.info("Signing the token with the private RSA Key");

    RSASSASigner signer = new RSASSASigner(rsaKeys.getPrivate());

    signedJWT.sign(signer);

    log.info("Serialized token '{}'", signedJWT.serialize());

    return signedJWT;
  }

  private JWTClaimsSet createJWTClaimSet(Authentication auth, Employee applicationUser) {
    log.info("Creating the JwtClaimSet Object for '{}'", applicationUser);

    return new JWTClaimsSet.Builder()
        .subject(applicationUser.getUsername())
        .claim(
            "authorities",
            auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()))
        .issuer("https://github.com/robsonsilv4")
        .issueTime(new Date())
        .expirationTime(
            new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
        .build();
  }

  @SneakyThrows
  private KeyPair generateKeyPair() {
    log.info("Gerando chaves RSA 2048 bits...");

    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);

    return generator.genKeyPair();
  }

  private String encryptToken(SignedJWT signedJWT) throws JOSEException {
    log.info("Iniciando o metódo 'encryptToken'...");

    DirectEncrypter directEncrypter =
        new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());

    JWEObject jweObject =
        new JWEObject(
            new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                .contentType("JWT")
                .build(),
            new Payload(signedJWT));

    log.info("Encriptando o token com a chave privada do sistema...");

    jweObject.encrypt(directEncrypter);

    log.info("Token criptografado!");

    return jweObject.serialize();
  }
}
