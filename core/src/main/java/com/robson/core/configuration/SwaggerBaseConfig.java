package com.robson.core.configuration;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerBaseConfig {
  private final String basePackage;

  public SwaggerBaseConfig(String basePackage) {
    this.basePackage = basePackage;
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .build()
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("API de Registro de Horas Trabalhadas")
        .description("API desenvolvida durante o desafio da GreenMile - Quixadá")
        .version("1.0")
        .contact(
            new Contact(
                "Robson Silva", "https://github.com/robsonsilv4", "robsonsilv410@gmail.com"))
        .license("MIT License")
        .licenseUrl("https://opensource.org/licenses/MIT")
        .build();
  }
}
