package com.example.petnote.dto;

import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;

@Data

public class MemberDTO {

    private String id;

    private String name;

    private String password;

    private String email;

    private Date birthdate;
}
