package com.robson.token.configurations;

import com.robson.core.configuration.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
  protected final JwtConfiguration jwtConfiguration;

  private static final String[] PUBLIC_MATCHERS_GET = {"/employees/**", "/hours/**"};

  private static final String[] PUBLIC_MATCHERS_POST = {"/employees/**", "/login/**"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
        .and()
        .sessionManagement()
        .sessionCreationPolicy(STATELESS)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(
            (req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .authorizeRequests()
        .antMatchers(jwtConfiguration.getLoginUrl(), "/**/swagger-ui.html")
        .permitAll()
        .antMatchers(
            HttpMethod.GET,
            "/**/swagger-resources/**",
            "/**/webjars/springfox-swagger-ui/**",
            "/**/v2/api-docs/**")
        .permitAll()
        .antMatchers(HttpMethod.DELETE, "/employees/**")
        .hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
        .permitAll()
        .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
        .permitAll()
        .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
        .hasAnyRole()
        .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
        .hasAnyRole()
        .anyRequest()
        .authenticated();
  }
}
