package com.example.petnote.controller;

import com.example.petnote.dto.MemberDTO;
import com.example.petnote.service.MemberServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/MemberLoginView")
    public String loginview(Model model, MemberDTO memberDTO) {

        model.addAttribute("login", memberServiceImp.memberLogin(memberDTO.getId(), memberDTO.getPassword()));


        return "./login/login_view";

    }


    @GetMapping("/MemberSignUp")

    public String memberInsert() {

        return "./signup/signup";

    }

    @GetMapping(value="/IdCheck" , produces = "application/json")
    @ResponseBody
    public Map<String, Integer> idcheck(HttpServletRequest request, HttpServletResponse response){
        //=============아이디 중복확인 get컨트롤러================
        Map<String, Integer> resultMap = new HashMap<>();
        int result = memberServiceImp.memberIdCheck(request,response);

        if (result == 0) {
            System.out.println("등록 가능한아이디");
        } else if (result == 1) {
            System.out.println("이미 등록된 아이디");
        }

        resultMap.put("result", result);
        return resultMap;
    }


    @PostMapping("/MemberSignUp")

    public String memberInsert(Model model, MemberDTO memberDTO) {

        model.addAttribute("member",memberServiceImp.memberSignUp(memberDTO));
        System.out.println("셍;"+ memberDTO.getId());

        return "./signup/signup_view";

    }
}
