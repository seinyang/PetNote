package com.example.petnote.controller;

import com.example.petnote.dto.MemberDTO;
import com.example.petnote.dto.kakaoUserInfoResponseDto;
import com.example.petnote.service.MemberServiceImp;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    final MemberServiceImp memberServiceImp;

    @GetMapping("/petnote")
    public String home(HttpSession session,Model model){
        Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

        kakaoUserInfoResponseDto userInfo = (kakaoUserInfoResponseDto) session.getAttribute("userInfo");
        String accessToken = (String) session.getAttribute("accessToken");

        if (isAuthenticated != null && isAuthenticated) {
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("accessToken",accessToken);
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        return "home";

    }

    @GetMapping("/MemberLogin")

    public String login() {

        return "./login/login";

    }

    @PostMapping("/MemberLogin")
    public ResponseEntity<MemberDTO> login(@RequestBody Map<String, String> loginData) {
        String id = loginData.get("id");
        String password = loginData.get("password");

        MemberDTO member = memberServiceImp.memberLogin(id, password);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/IdSearch")

    public String idSearch() {

        return "/login/id_search";

    }
    @GetMapping("/IdSearchView")

    public String idSearchResult() {

        return "/login/id_search_view";

    }
    @PostMapping("/IdSearch")

    public ResponseEntity<String> idSearch(@RequestBody Map<String, String> searchIdData) {

        String name =searchIdData.get("name");
        String email = searchIdData.get("email");

        String id = memberServiceImp.memberIdSearch(name,email);

        if (id != null) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 아이디를 찾을수 없습니다.");
        }
    }
    @GetMapping("/PasswordSearch")

    public String PasswordSearch() {

        return "/login/password_search";

    }

    @GetMapping("/PasswordSearchView")

    public String PasswordSearchView() {

        return "/login/password_search_view";

    }

    @PostMapping("/PasswordSearch")

    public ResponseEntity<String> PasswordSearch(@RequestBody Map<String, String> PasswordData) {

        String id =PasswordData.get("id");
        String email = PasswordData.get("email");

        String password = memberServiceImp.PasswordSearch(id,email);

        if (id != null) {
            return ResponseEntity.ok(password);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 비밀번호를 찾을수 없습니다.");
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @PostMapping("/MemberSignUp")

    public ResponseEntity<?> memberInsert(@RequestBody MemberDTO memberDTO) {
        if (memberServiceImp.isIdExist(memberDTO.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ID already exists");
        }
        MemberDTO createdMember = memberServiceImp.memberSignUp(memberDTO);

        if (createdMember != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/CheckId/{id}")
    public ResponseEntity<?> checkId(@PathVariable String id) {
        boolean exists = memberServiceImp.isIdExist(id);
        return ResponseEntity.ok(exists);
    }
}
