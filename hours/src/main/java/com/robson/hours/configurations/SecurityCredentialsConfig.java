package com.robson.hours.configurations;

import com.robson.core.configuration.JwtConfiguration;
import com.robson.token.configurations.SecurityTokenConfig;
import com.robson.token.converter.TokenConverter;
import com.robson.token.filters.JwtTokenAuthorizationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
  private final TokenConverter tokenConverter;

  public SecurityCredentialsConfig(
      JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
    super(jwtConfiguration);
    this.tokenConverter = tokenConverter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterAfter(
        new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
        UsernamePasswordAuthenticationFilter.class);
    super.configure(http);
  }
}
