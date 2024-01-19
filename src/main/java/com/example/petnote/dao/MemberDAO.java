package com.example.petnote.dao;

import com.example.petnote.dto.MemberDTO;

public interface MemberDAO {

    public MemberDTO memberLogin(String id,String Password);

    public void memberSignUp(MemberDTO memberDTO);
}
