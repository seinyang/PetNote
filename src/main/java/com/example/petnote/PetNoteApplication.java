package com.example.petnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.petnote.repository")
@EntityScan(basePackages = "com.example.petnote.entity")
public class PetNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetNoteApplication.class, args);
    }

}
