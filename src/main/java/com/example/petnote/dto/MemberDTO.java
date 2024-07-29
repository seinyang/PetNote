package com.example.petnote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;

@Data

public class MemberDTO {

    private String id;

    private String name;

    private String password;

    private String email;

    private Date birthday;
}
