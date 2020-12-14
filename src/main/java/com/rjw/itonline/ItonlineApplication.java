package com.rjw.itonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rjw.itonline.edu.mapper")
public class ItonlineApplication {
    public static void main(String[] args) {
        SpringApplication.run (ItonlineApplication.class, args);
    }
}
