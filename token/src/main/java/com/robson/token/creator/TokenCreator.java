package com.robson.token.creator;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenCreator {

  private final JwtConfiguration jwtConfiguration;

  @SneakyThrows
  public SignedJWT createSignedJWT(Authentication auth) {

    log.info("Iniciando o processo de criação do JWT assinado...");

    Employee employee = (Employee) auth.getPrincipal();

    JWTClaimsSet jwtClaimSet = createJWTClaimSet(auth, employee);

    KeyPair rsaKeys = generateKeyPair();

    log.info("Construindo o JWK a partir das chaves RSA...");

    JWK jwk =
        new RSAKey.Builder((RSAPublicKey) rsaKeys.getPublic())
            .keyID(UUID.randomUUID().toString())
            .build();

    SignedJWT signedJWT =
        new SignedJWT(
            new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).type(JOSEObjectType.JWT).build(),
            jwtClaimSet);

    log.info("Assinando o token com a chave RSA privada...");

    RSASSASigner signer = new RSASSASigner(rsaKeys.getPrivate());

    signedJWT.sign(signer);

    log.info("Serializando o token '{}'...", signedJWT.serialize());

    return signedJWT;
  }

  private JWTClaimsSet createJWTClaimSet(Authentication auth, Employee employee) {
    log.info("Criando o objeto JwtClaimSet para '{}'...", employee);

    return new JWTClaimsSet.Builder()
        .subject(employee.getUsername())
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
    log.info("Gerando as chaves RSA de 2048 bits...");

    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

    generator.initialize(2048);

    return generator.genKeyPair();
  }

  public String encryptToken(SignedJWT signedJWT) throws JOSEException {
    log.info("Iniciando o método 'encryptToken'...");

    DirectEncrypter directEncrypter =
        new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());

    JWEObject jweObject =
        new JWEObject(
            new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                .contentType("JWT")
                .build(),
            new Payload(signedJWT));

    log.info("Criptografando o token com as chaves privadas do sistema...");

    jweObject.encrypt(directEncrypter);

    log.info("Token criptografado!");

    return jweObject.serialize();
  }
}
