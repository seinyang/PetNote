package com.example.petnote.service;

import com.example.petnote.dto.MemberDTO;

public interface MemberService {

    public MemberDTO memberLogin(String id,String password);

    public MemberDTO memberSignUp(MemberDTO memberDTO);

    boolean isIdExist(String id); // 추가

    public String memberIdSearch(String name, String email);
}
