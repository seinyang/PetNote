package com.example.petnote.service;



import com.example.petnote.dto.PetDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PetService {
	
	public List<PetDTO> petSelectAll();
	public PetDTO petDetail(PetDTO petDTO);
	public PetDTO petInsert(PetDTO petDTO);
	public void petUpdate(PetDTO petDTO);
	public void petDelete(String name);
	public int petNameCheck(HttpServletRequest request, HttpServletResponse response);
	

}
