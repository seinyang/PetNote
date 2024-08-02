package com.example.petnote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.petnote.mapper")
public class PetNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetNoteApplication.class, args);
    }

}
