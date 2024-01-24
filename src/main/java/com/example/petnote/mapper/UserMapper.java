package com.example.petnote.mapper;

import com.example.petnote.dto.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("SELECT id, password FROM petnote.member WHERE id = #{id} AND password = #{password}")
    MemberDTO login(@Param("id") String id, @Param("password") String password);

    @Insert("INSERT INTO petnote.member(id, password, name, email, birthdate) " +
            "VALUES (#{id}, #{password}, #{name}, #{email}, #{birthdate}) " +
            "ON DUPLICATE KEY UPDATE id = id")
    void signup(MemberDTO memberDTO);

    @Select("SELECT * FROM petnote.member WHERE id = #{id}")
    MemberDTO idcheck(@Param("id") String id);


}
