package com.shihui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shihui.*.mapper")
public class ZzctestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzctestApplication.class, args);
    }

}
