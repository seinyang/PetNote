package com.example.petnote.dao;

import com.example.petnote.dto.MemberDTO;
import com.example.petnote.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor

public class MemberDaoImp implements MemberDAO {

    final UserMapper mapper;

    @Override
    public MemberDTO memberLogin(String id, String password) {

        return mapper.login(id,password);
    }

    @Override
    public void memberSignUp(MemberDTO memberDTO) {
        mapper.signup(memberDTO);
    }


    public MemberDTO memberId(String id) {
        return mapper.idcheck(id);
    }
}