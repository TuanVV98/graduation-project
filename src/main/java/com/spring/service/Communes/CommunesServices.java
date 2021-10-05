package com.spring.service.Communes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.CommunesDTO;
import com.spring.repository.CommunesRepository;


@Service
public class CommunesServices {
	@Autowired
	CommunesRepository communesRepository;
	

	public List<CommunesDTO> findAll() {
		List<CommunesDTO> itemsDTO = new ArrayList<>();
		communesRepository.findAll().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
		return itemsDTO;
	}

	public CommunesDTO findById(String id) {
		return communesRepository.findById(id).get().convertEntityToDTO();
	}
	
	public boolean existById (String id) {
		if(communesRepository.existsById(id)) {
			return true;
		}
		return false;
	}
	
}
