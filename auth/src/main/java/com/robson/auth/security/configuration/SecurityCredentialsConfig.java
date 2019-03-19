package com.robson.auth.security.configuration;

import com.robson.auth.security.filters.JwtUsernameAndPasswordAuthenticationFilter;
import com.robson.core.configuration.JwtConfiguration;
import com.robson.token.configurations.SecurityTokenConfig;
import com.robson.token.converter.TokenConverter;
import com.robson.token.creator.TokenCreator;
import com.robson.token.filters.JwtTokenAuthorizationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

  private final UserDetailsService userDetailsService;
  private final TokenCreator tokenCreator;
  private final TokenConverter tokenConverter;

  public SecurityCredentialsConfig(
      JwtConfiguration jwtConfiguration,
      @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
      TokenCreator tokenCreator,
      TokenConverter tokenConverter) {
    super(jwtConfiguration);
    this.userDetailsService = userDetailsService;
    this.tokenCreator = tokenCreator;
    this.tokenConverter = tokenConverter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilter(
            new JwtUsernameAndPasswordAuthenticationFilter(
                authenticationManager(), jwtConfiguration, tokenCreator))
        .addFilterAfter(
            new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
            UsernamePasswordAuthenticationFilter.class);
    super.configure(http);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
