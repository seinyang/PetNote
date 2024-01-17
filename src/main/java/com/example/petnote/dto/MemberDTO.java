package com.example.petnote.dto;

import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Data

public class MemberDTO {

    private String id;

    private String name;

    private String password;

    private String email;

    private int birthdate;
}
