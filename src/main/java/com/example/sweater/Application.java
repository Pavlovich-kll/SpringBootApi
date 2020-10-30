package com.example.sweater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //находит все контроллеры и тд, чтобы запустить приложение
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}