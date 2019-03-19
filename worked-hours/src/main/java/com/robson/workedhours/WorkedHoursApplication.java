package com.robson.workedhours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.robson.core.domains"})
@EnableJpaRepositories({"com.robson.core.repositories"})
public class WorkedHoursApplication {

  public static void main(String[] args) {
    SpringApplication.run(WorkedHoursApplication.class, args);
  }
}
