package com.robson.desafiogreenmile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesafioGreenmileApplication.class)
@TestPropertySource("classpath:application.properties")
public class DesafioGreenmileApplicationTests {

  @Test
  public void contextLoads() {}
}
