package com.example.board_0914;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
public class Board0914Application {

  public static void main(String[] args) {
    SpringApplication.run(Board0914Application.class, args);
  }

}
