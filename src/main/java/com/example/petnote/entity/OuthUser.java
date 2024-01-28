package com.example.petnote.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "outh_user")
public class OuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    private String kakaotalkAccessToken;


}

