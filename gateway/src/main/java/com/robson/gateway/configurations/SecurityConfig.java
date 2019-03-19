package com.robson.gateway.configurations;

import com.robson.core.configuration.JwtConfiguration;
import com.robson.gateway.filters.GatewayJwtTokenAuthorizationFilter;
import com.robson.token.configurations.SecurityTokenConfig;
import com.robson.token.converter.TokenConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {
  private final TokenConverter tokenConverter;

  public SecurityConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
    super(jwtConfiguration);
    this.tokenConverter = tokenConverter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterAfter(
        new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
        UsernamePasswordAuthenticationFilter.class);
    super.configure(http);
  }
}
