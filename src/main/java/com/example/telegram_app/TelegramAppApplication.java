package com.example.telegram_app;

import com.example.telegram_app.controller.WebHookController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(TelegramAppApplication.class, args);


    }

}
