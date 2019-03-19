package com.robson.hours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.robson.core.domains"})
@EnableJpaRepositories({"com.robson.core.repositories"})
public class HoursApplication {

  public static void main(String[] args) {
    SpringApplication.run(HoursApplication.class, args);
  }
}
