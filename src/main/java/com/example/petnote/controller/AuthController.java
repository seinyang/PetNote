package com.example.petnote.controller;

import com.example.petnote.dto.kakaoUserInfoResponseDto;
import com.example.petnote.service.kakaoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class AuthController {

    @org.springframework.beans.factory.annotation.Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    private final kakaoService kakaoService;


    @GetMapping("/MemberSignUp")
    public String memberInsert(Model model) {

        String kakaoLocation = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + kakaoClientId + "&redirect_uri=" + kakaoRedirectUri;
        model.addAttribute("kakaoLocation", kakaoLocation);

        return "/signup/signup";

    }

    // 카카오 로그인에서 동의 버튼을 누르면 ,카카오 인증서버가 ? 인가코드를 콜백 url에 전달
    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code, HttpSession session)throws IOException {

        // 인가 코드(code)로 액세스 토큰을 요청
        String accessToken = kakaoService.getAccessTokenKakao(code);
        //받아온 액세스 토큰을 사용하여 카카오사용자 정보를 요청
        kakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // 세션에 인증 정보 저장
        session.setAttribute("isAuthenticated", true);
        session.setAttribute("userInfo", userInfo);
        session.setAttribute("accessToken",accessToken);
        return "redirect:/petnote";

    }


}
