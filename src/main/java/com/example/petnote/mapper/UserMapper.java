package com.example.petnote.mapper;

import com.example.petnote.dto.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT id,password FROM petnote.member WHERE id = #{id}")
    MemberDTO login(@Param("id") String id);

}
