package com.example.petnote.mapper;

import com.example.petnote.dto.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT id,password FROM petnote.member WHERE id = #{id} AND password = #{password}")
    MemberDTO login(@Param("id") String id,@Param("password") String password);

    @Insert("INSERT INTO petnote.member(id,password,name,email,birthday) VALUES (#{id}, #{password}, #{name}, #{email},#{birthday})")
    void signup(MemberDTO memberDTO);

    @Select("SELECT COUNT(*) > 0 FROM petnote.member WHERE id = #{id}")
    boolean isIdExist(@Param("id") String id);

    @Select("SELECT id FROM petnote.member WHERE name = #{name} AND email = #{email} LIMIT 1")
    String IdSearch(@Param("name") String name,@Param("email") String email);
}
