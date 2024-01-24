package com.example.petnote.service;

import com.example.petnote.dao.MemberDAO;
import com.example.petnote.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

    final MemberDAO memberDAO;

    @Override
    public MemberDTO memberLogin(String id, String password) {

        MemberDTO member = memberDAO.memberLogin(id,password);

        if (member != null && member.getId().equals(id) && member.getPassword().equals(password)) {
            return member;
        }
        return null;
    }

    @Override
    public MemberDTO memberSignUp(MemberDTO memberDTO) {
        try {
            memberDAO.memberSignUp(memberDTO);

        }catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }

        return memberDTO;

    }

    @Override
    public  int memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
        //============= 서비스imp 아이디 중복확인 메서드==========

        int idcheck = 0;
        String id = request.getParameter("id");
        if (memberDAO.memberId(id) == null) {
            idcheck = 0;
        } else {
            idcheck = 1;
        }
        return idcheck;
    }


}
