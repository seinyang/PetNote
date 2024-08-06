package com.example.petnote.service;

import com.example.petnote.dto.naverTokenResponseDto;
import com.example.petnote.dto.naverUserInfoResponseDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class naverService {

    private final String clientId;
    private final String clientSecret;
    private final String AUTH_TOKEN_URL_HOST;
    private final String AUTH_USER_URL_HOST;

    @Autowired
    public naverService(@Value("${naver.client_id}") String clientId, @Value("${naver.client_secret}")String clientSecret){

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        AUTH_TOKEN_URL_HOST = "https://nid.naver.com";
        AUTH_USER_URL_HOST = "https://openapi.naver.com";

    }

    public String getAccessTokenNaver(String code){

        naverTokenResponseDto naverTokenResponseDto = WebClient.create(AUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth2.0/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id",clientId)
                        .queryParam("client_secret",clientSecret)
                        .queryParam("code",code)
                        .build(true))
                //헤더 부분의 컨텐트 타입은 하라는대로
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                // 요청에 대한 응답 상태를 확인하고 예외를 처리
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))


                .bodyToMono(com.example.petnote.dto.naverTokenResponseDto.class)
                .block();

        // 액세스 토큰 및 기타 토큰 정보를 로그로 출력
        log.info(" [naver Service] Access Token ------> {}", naverTokenResponseDto.getAccess_token());
        log.info(" [naver Service] Refresh Token ------> {}", naverTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우


        return naverTokenResponseDto.getAccess_token();
    }

    public naverUserInfoResponseDto getUserInfo(String accessToken){

        naverUserInfoResponseDto naverUserInfo = WebClient.create(AUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/nid/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                // 응답 상태가 4xx일 경우, 'Invalid Parameter' 예외를 발생
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                // 응답 상태가 5xx일 경우, 'Internal Server Error'
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))

                .bodyToMono(naverUserInfoResponseDto.class)
                .block();

        log.info("[ naver Service ] NAME ---> {} ", naverUserInfo.getResponse().getName());
        log.info("[ naver Service ] Email ---> {} ", naverUserInfo.getResponse().getEmail());


        return naverUserInfo;
    }

}
