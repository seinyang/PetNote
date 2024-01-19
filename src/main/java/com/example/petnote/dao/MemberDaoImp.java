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
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);
        return mapper.login(id);
    }

    @Override
    public void memberSignUp(MemberDTO memberDTO) {
        mapper.signup(memberDTO);
    }
}