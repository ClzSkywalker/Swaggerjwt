package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dao")
public class Demoswagger04Application {

    public static void main(String[] args) {
        SpringApplication.run(Demoswagger04Application.class, args);
    }

}
