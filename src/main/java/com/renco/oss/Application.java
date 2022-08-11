package com.renco.oss;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan("com.renco")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
