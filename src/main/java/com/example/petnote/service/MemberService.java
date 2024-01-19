package com.example.petnote.service;

import com.example.petnote.dto.MemberDTO;

public interface MemberService {

    public MemberDTO memberLogin(String id,String password);

    public MemberDTO memberSignUp(MemberDTO memberDTO);
}
