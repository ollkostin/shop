package ru.practice.kostin.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class);
    }
}
