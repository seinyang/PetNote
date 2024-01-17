package com.example.petnote.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller

public class Controller {


    @GetMapping("/petnote")
    public String home(Model model){
        model.addAttribute("message","hello");
        return "home";

    }

}
