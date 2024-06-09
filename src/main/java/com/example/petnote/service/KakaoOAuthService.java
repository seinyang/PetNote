//package com.example.petnote.service;
//
//
//import com.example.petnote.dto.OauthDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.time.Instant;
//
//@Service
//
//public class KakaoOAuthService {
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    // application.properties 또는 application.yml에 설정된 값
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
//    // application.properties 또는 application.yml에 설정된 값
//    private String clientSecret;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
//    // application.properties 또는 application.yml에 설정된 값
//    private String redirectUri;
//
//    private static final RestTemplate restTemplate = new RestTemplate();
//
//    public String generateKakaoLoginUrl() {
//        // kakao 로그인 URL 생성
//        String authUrl = "https://kauth.kakao.com/oauth/authorize";
//
//        return authUrl + "?client_id=" + clientId + "&redirect_uri=" + redirectUri+  "&response_type=code" ;
//
//    }
//
//    public String getAccessToken(String code) throws JsonProcessingException {
//        // Kakao로부터 엑세스 토큰 요청
//        String tokenUrl = "https://kauth.kakao.com/oauth/token";
//
//        // query parameter를 적절한 방식으로 구성
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(tokenUrl)
//                .queryParam("client_id", clientId)
//                .queryParam("client_secret", clientSecret)
//                .queryParam("code", code)
//                .queryParam("redirect_uri", redirectUri)
//                .queryParam("grant_type", "authorization_code");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(headers), String.class);
//        String responseBody = response.getBody();
//
//        // JSON 파싱
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(responseBody);
//
//        // 에러 확인
//        if (jsonNode.has("error")) {
//            String errorCode = jsonNode.path("error").asText();
//            if ("invalid_grant".equals(errorCode)) {
//                // KOE320 에러 발생시, 새로운 인가코드로 토큰 다시 요청
//                return refreshAccessToken(jsonNode.path("refresh_token").asText());
//            }
//            // 다른 에러인 경우 예외 처리 또는 다른 조치 수행
//            throw new RuntimeException("Error during access token retrieval: " + errorCode);
//        }
//
//        // 엑세스 토큰을 파싱
//        String accessToken = jsonNode.path("access_token").asText();
//        String expiresIn = jsonNode.path("expires_in").asText();
//
//        // 만료 시간 확인
//        long expiresInMillis = Long.parseLong(expiresIn) * 1000;
//        long expirationTimeMillis = System.currentTimeMillis() + expiresInMillis;
//
//        // 현재 시간과 만료 시간 비교
//        if (expirationTimeMillis <= System.currentTimeMillis()) {
//            // 토큰이 만료되었으므로 재인증이 필요
//            return refreshAccessToken(jsonNode.path("refresh_token").asText());
//        }
//
//        return accessToken;
//    }
//
//
//
//        public static OauthDTO getUserInfo(String accessToken) {
//        String apiUrl = "https://kapi.kakao.com/v2/user/me"; // KAKAO API 사용자 정보 엔드포인트
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer "+accessToken);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<OauthDTO> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, OauthDTO.class);
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            return responseEntity.getBody();
//        } else {
//            return null;
//        }
//    }
//
//    public String refreshAccessToken(String refreshToken) throws JsonProcessingException {
//        // Kakao로부터 새로운 엑세스 토큰 요청
//        String tokenUrl = "https://kauth.kakao.com/oauth/token";
//
//        // query parameter를 적절한 방식으로 구성
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(tokenUrl)
//                .queryParam("client_id", clientId)
//                .queryParam("client_secret", clientSecret)
//                .queryParam("refresh_token", refreshToken)
//                .queryParam("grant_type", "refresh_token");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(headers), String.class);
//        String responseBody = response.getBody();
//
//        // JSON 파싱
//        JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
//
//        // 에러 확인
//        if (jsonNode.has("error")) {
//            // 에러가 발생한 경우 예외 처리 또는 다른 조치 수행
//            throw new RuntimeException("Error during access token refresh: " + jsonNode.path("error").asText());
//        }
//
//        // 새로 발급받은 엑세스 토큰 반환
//        return jsonNode.path("access_token").asText();
//    }
//}