package com.example.petnote.controller;

import com.example.petnote.dto.MemberDTO;
import com.example.petnote.service.MemberServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    final MemberServiceImp memberServiceImp;

    @GetMapping("/petnote")
    public String home(Model model){

        return "home";

    }

    @GetMapping("/MemberLogin")

    public String login() {

        return "./login/login";

    }

    @GetMapping("/MemberSignUp")

    public String memberInsert() {

        return "./signup/signup";

    }

    @PostMapping("/MemberSignUp")

    public String memberInsert(Model model, MemberDTO memberDTO) {

        model.addAttribute("member",memberServiceImp.memberSignUp(memberDTO));

        return "./signup/signup_view";

    }
}
