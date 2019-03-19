package com.robson.hours.configurations;

import com.robson.core.configuration.SwaggerBaseConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends SwaggerBaseConfig {
  public SwaggerConfig() {
    super("com.robson.hours.repositories");
  }
}
