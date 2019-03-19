package com.robson.auth.configurations;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerBaseConfig extends com.robson.core.configuration.SwaggerBaseConfig {
  public SwaggerBaseConfig() {
    super("com.robson.auth.repositories");
  }
}
