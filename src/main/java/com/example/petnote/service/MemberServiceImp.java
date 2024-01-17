package com.example.petnote.service;

import com.example.petnote.dao.MemberDAO;
import com.example.petnote.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

    final MemberDAO memberDAO;

    @Override
    public MemberDTO memberLogin(String id, String password) {

        MemberDTO member = memberDAO.memberLogin(id,password);

        if (member == null){

            return null;

        }
        return member;
    }
}
