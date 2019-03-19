package com.robson.hours;

import com.robson.core.configuration.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.robson")
@EntityScan({"com.robson.core.domains"})
@EnableJpaRepositories({"com.robson.core.repositories"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class HoursApplication {

  public static void main(String[] args) {
    SpringApplication.run(HoursApplication.class, args);
  }
}
