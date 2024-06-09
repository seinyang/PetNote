package com.example.petnote.dao;


import com.example.petnote.dto.PetDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor

public class PetDAOImp implements PetDAO{
	

	
	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<PetDTO> petSelectAll() {

		return 	sqlSessionTemplate.selectList("ysi.spring.pet.selectAll");
	}

	@Override
	public void petInsert(PetDTO petDTO) {
	 sqlSessionTemplate.insert("ysi.spring.pet.insert",petDTO);
		
	}

	@Override
	public void petUpdate(PetDTO petDTO) {
		
	sqlSessionTemplate.update("ysi.spring.pet.update",petDTO);
	}

	@Override
	public void petDelete(String name) {
	sqlSessionTemplate.delete("ysi.spring.pet.delete",name);
	}

	@Override
	public PetDTO petDetail(PetDTO petDTO) {
		
		return sqlSessionTemplate.selectOne("ysi.spring.pet.selectDetail",petDTO);
	}

	@Override
	public PetDTO petNameCheck(String name) {
	
		return sqlSessionTemplate.selectOne("ysi.spring.pet.selectDetail",name);
	}

}
