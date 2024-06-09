package com.example.petnote.mapper;

import com.example.petnote.dto.PetDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PetMapper {

    @Select("SELECT * FROM petmember")
    List<PetDTO> selectAll();

    @Insert("INSERT INTO petmember (name, breed, gender, age, psize) " +
            "VALUES (#{name}, #{breed}, #{gender}, #{age}, #{psize})")
    void insert(PetDTO petDTO);

    @Update("UPDATE petmember SET name = #{name}, breed = #{breed}, gender = #{gender}, age = #{age}, psize = #{psize} " +
            "WHERE name = #{name}")
    void update(PetDTO petDTO);

    @Select("SELECT * FROM petmember WHERE name = #{name}")
    PetDTO selectDetail(@Param("name") String name);

    @Delete("DELETE FROM petmember WHERE name = #{name}")
    void delete(@Param("name") String name);
}