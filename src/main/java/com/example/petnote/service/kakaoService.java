package com.example.petnote.service;


import com.example.petnote.dto.kakaoTokenResponseDto;
import com.example.petnote.dto.kakaoUserInfoResponseDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class kakaoService {

    private final String clientId;
    private final String clientSecret;
    private final String KAUTH_TOKEN_URL_HOST;
    private final String KAUTH_USER_URL_HOST;

    @Autowired
    public kakaoService(@Value("${kakao.client_id}") String clientId, @Value("${kakao.client_secret}") String clientSecret){
        // 클라이언트 시크릿 및 클라이언트 아이디와 함께 카카오 인증 서버의 url을 초기화한다
        this.clientId = clientId;
        this.clientSecret=clientSecret;
        KAUTH_TOKEN_URL_HOST ="https://kauth.kakao.com";
        KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
    }


    public String getAccessTokenKakao(String code) {
        // WebClient를 사용하여 POST 요청을 통해 카카오 인증 서버에서 액세스 토큰을 받아온다
        kakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        // HTTPS 스킴을 사용하여 카카오의 토큰 발급 주소로 연결
                        .scheme("https")
                        .path("/oauth/token")
                        // 필수 파라미터를 포함하여 요청을 구성
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("code", code)
                        .build(true))
                //헤더 부분의 컨텐트 타입은 하라는대로
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                // 요청에 대한 응답 상태를 확인하고 예외를 처리
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))

                //응답하는 body를 KakaoTokenResponseDto로 반환해준다
                .bodyToMono(com.example.petnote.dto.kakaoTokenResponseDto.class)
                .block();

        // 액세스 토큰 및 기타 토큰 정보를 로그로 출력
        log.info(" [Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info(" [Kakao Service] Id Token ------> {}", kakaoTokenResponseDto.getIdToken());
        log.info(" [Kakao Service] Scope ------> {}", kakaoTokenResponseDto.getScope());

        return kakaoTokenResponseDto.getAccessToken();
    }
}
