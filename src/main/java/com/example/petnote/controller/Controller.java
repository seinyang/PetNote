package com.example.petnote.controller;

import com.example.petnote.dto.MemberDTO;
import com.example.petnote.dto.OauthDTO;
import com.example.petnote.entity.OuthUser;
import com.example.petnote.repository.AuthUserRepository;
//import com.example.petnote.service.KakaoOAuthService;
import com.example.petnote.service.MemberServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    final MemberServiceImp memberServiceImp;
//    final KakaoOAuthService kakaoOAuthService;
    final AuthUserRepository authUserRepository;


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
//=================아이디 중복체크=========
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


//    @GetMapping("/kakao/login")
//    public String redirectToKakaoLogin(HttpServletResponse response) {
//        String kakaoLoginUrl = kakaoOAuthService.generateKakaoLoginUrl();
//        // 실제로 사용자를 해당 URL로 리다이렉트
//        return "redirect:"+kakaoLoginUrl;
//    }
//
//    @GetMapping ("/login/kakaotalk")
//    public ResponseEntity<String> handleKakaoCallback(@RequestParam("code") String code) throws JsonProcessingException {
//
//        String accessToken = kakaoOAuthService.getAccessToken(code);
//        System.out.println("=====================Callback endpoint called with code: " + accessToken);
//        // GitHub API를 사용하여 사용자 정보 가져오기
//        OauthDTO oauthDTO = KakaoOAuthService.getUserInfo(accessToken);
//
//        OuthUser kakaoUser = new OuthUser();
//        kakaoUser.setEmail(oauthDTO.getEmail());
//        kakaoUser.setPassword(oauthDTO.getPassword());
//
//        // 사용자 정보를 데이터베이스에 저장
//
//        try {
//            // 사용자 정보를 데이터베이스에 저장
//            authUserRepository.save(kakaoUser);
//        } catch (Exception e) {
//            // 예외 처리: 데이터베이스 작업 중에 예외가 발생할 수 있으므로 예외 처리를 고려해야 합니다.
//            e.printStackTrace(); // 예외 처리 방식을 선택하여 수정해야 합니다.
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터베이스 오류");
//        }
////        return ResponseEntity.ok("GitHub OAuth2 인증이 완료되었습니다.");
//
//        return ResponseEntity.ok("카카오톡 인증이 완료되었습니다");
//    }

}
