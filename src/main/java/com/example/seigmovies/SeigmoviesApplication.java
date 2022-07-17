package com.example.seigmovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SeigmoviesApplication {

    public static void main(String[] args) {
        // 防止附件名过长给截断
        System.getProperties().setProperty("mail.mime.splitlongparameters","false");
        SpringApplication.run(SeigmoviesApplication.class, args);
    }

}
