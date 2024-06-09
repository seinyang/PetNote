package com.example.petnote.dao;



import com.example.petnote.dto.PetDTO;

import java.util.List;

public interface PetDAO {

public List<PetDTO> petSelectAll();
public PetDTO petDetail(PetDTO petDTO);
public PetDTO petNameCheck(String name);
public void petInsert(PetDTO petDTO);
public void petUpdate(PetDTO petDTO);
public void petDelete(String name);



}
