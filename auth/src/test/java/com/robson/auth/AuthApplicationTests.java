package com.robson.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthApplicationTests {

  @Test
  public void contextLoads() {}

  @Test
  public void test() {
    System.out.println(new BCryptPasswordEncoder().encode("desafio"));
  }
}
