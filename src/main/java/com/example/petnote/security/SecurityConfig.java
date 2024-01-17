package com.example.petnote.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").authenticated() // "/" 경로에 대해 인증이 필요함
                .anyRequest().permitAll() // 나머지 요청은 인증 없이 허용
                .and()
                .formLogin()
                .loginPage("/login") // 로그인 페이지 경로 설정
                .permitAll();
    }
}
