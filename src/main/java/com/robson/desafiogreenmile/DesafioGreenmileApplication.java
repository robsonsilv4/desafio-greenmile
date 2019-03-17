package com.robson.desafiogreenmile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DesafioGreenmileApplication {

  public static void main(String[] args) {
    SpringApplication.run(DesafioGreenmileApplication.class, args);
  }
}
