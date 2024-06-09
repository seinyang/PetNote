package com.example.petnote.service;


import com.example.petnote.dao.PetDAO;
import com.example.petnote.dto.PetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PetServiceImp implements PetService{
	private final PetDAO petDAO;
	private static final Logger logger = LoggerFactory.getLogger(PetServiceImp.class);
	@Autowired
	public PetServiceImp(/* @Qualifier("PetDAOImp") */ PetDAO petDAO) {
	this.petDAO=petDAO;
	}

	@Override
	public List<PetDTO> petSelectAll() {
	
		return petDAO.petSelectAll();
	}


	@Override
	public PetDTO petInsert(PetDTO petDTO) {
		try {
			petDAO.petInsert(petDTO);
		}catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}
		return petDTO;
	}
	

	@Override
	public void petUpdate(PetDTO petDTO) {
		petDAO.petUpdate(petDTO);
	}

	@Override
	public void petDelete(String name) {
		 petDAO.petDelete(name);
	}

	@Override
	public PetDTO petDetail(PetDTO petDTO) {
		
		return petDAO.petDetail(petDTO);
	}

	@Override
	public int petNameCheck(HttpServletRequest request, HttpServletResponse response) {
		logger.info("============= 서비스imp 아이디 중복확인 메서드==========");

		int namecheck = 0;
		String name = request.getParameter("name");
		petDAO.petNameCheck(name);

		if (petDAO.petNameCheck(name) == null) {
			namecheck = 0;
		} else {
			namecheck = 1;
		}
		return namecheck;
	}



}
