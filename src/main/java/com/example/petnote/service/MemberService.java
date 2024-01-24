package com.example.petnote.service;

import com.example.petnote.dto.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {

    public MemberDTO memberLogin(String id,String password);

    public MemberDTO memberSignUp(MemberDTO memberDTO);

    public int memberIdCheck(HttpServletRequest request, HttpServletResponse response);
}
