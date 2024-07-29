package com.example.petnote.controller;

import com.example.petnote.dto.MemberDTO;
import com.example.petnote.service.MemberServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @PostMapping("/MemberLogin")
    @GetMapping("/IdSearch")

    public String idSearch() {

        return "/login/id_search";

    }

    @GetMapping("/MemberSignUp")

    public String memberInsert() {

        return "./signup/signup";

    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @PostMapping("/MemberSignUp")

    public String memberInsert( MemberDTO memberDTO) {

        memberServiceImp.memberSignUp(memberDTO);

        return "./signup/signup_view";

    }
}
