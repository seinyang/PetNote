package com.example.petnote.dao;

import com.example.petnote.dto.MemberDTO;

public interface MemberDAO {

    public MemberDTO memberLogin(String id,String Password);

    public void memberSignUp(MemberDTO memberDTO);

    public boolean isIdExist(String id);

    public String IdSearch(String name,String email);

    public String PasswordSearch(String id,String email);
}
