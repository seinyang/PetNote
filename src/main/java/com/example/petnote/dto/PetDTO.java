package com.example.petnote.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class PetDTO {

    private String name;
    private String breed;
    private String gender;
    private int age;
    private String psize;

}
