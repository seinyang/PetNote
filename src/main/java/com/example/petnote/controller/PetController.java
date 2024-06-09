package com.example.petnote.controller;

import com.example.petnote.dto.PetDTO;
import com.example.petnote.service.PetServiceImp;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PetController {
	
	private static final Logger logger = LoggerFactory.getLogger(PetController.class);
	
@Autowired
private final PetServiceImp petServiceImp;

@GetMapping("/PetList")
public String List(Model model) {
	model.addAttribute("list",petServiceImp.petSelectAll());
	logger.info("list",model);
	return "petmember/pet_select_view.jsp";
	
}

@GetMapping("/PetMemberInsert")

public String petmemberinsert() {

	return "";

}
@PostMapping("/PetMemberInsert")

public String petmemberinsert(Model model, PetDTO petDTO) {
	model.addAttribute("pet",petServiceImp.petInsert(petDTO));
	return "petmember/petmember_insert_view";

}

@GetMapping("/PetDetail")

public String petdetail(Model model,PetDTO petDTO) {
	model.addAttribute("list",petServiceImp.petDetail(petDTO));
	return "petmember/pet_select_detail_view";

}

@GetMapping("/PetUpdate")

public String petupdate(Model model,PetDTO petDTO) {
	model.addAttribute("pet",petServiceImp.petDetail(petDTO));
	return "./petmember/pet_update";

}
@PostMapping("/PetUpdate")

public String petupdate(PetDTO petDTO) {
	petServiceImp.petUpdate(petDTO);
	return "./petmember/pet_update_view";

}


@GetMapping("/PetDelete")

public String petdelete(Model model,PetDTO petDTO) {
		/* model.addAttribute("list", petServiceImp.petDetail(petDTO)); */
	PetDTO pet = petServiceImp.petDetail(petDTO);
    model.addAttribute("list", pet);
	return "petmember/pet_delete.jsp";

}
@PostMapping("/PetDelete")

public String petdelete(PetDTO petDTO) {
	petServiceImp.petDelete(petDTO.getName());
	return "./petmember/pet_delete_view";

}

@GetMapping(value="/NameCheck",produces="application/json")
@ResponseBody
	public Map<String, Integer> idcheck(HttpServletRequest request, HttpServletResponse response) {
		logger.info("=============아이디 중복확인 get컨트롤러================");
		Map<String, Integer>resultMap = new HashMap<>();
		int result = petServiceImp.petNameCheck(request, response);

			if (result == 0) {
				logger.info("등록 가능한 아이디 ");
			}else if (result == 1) {
				logger.info("이미 등록된 아이디");
			}	
			resultMap.put("result", result);
		return resultMap;
	}
}
