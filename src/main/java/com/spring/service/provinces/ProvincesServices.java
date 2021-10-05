package com.spring.service.provinces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.ProvincesDTO;
import com.spring.repository.ProvincesRepository;

@Service
public class ProvincesServices {
	@Autowired
	ProvincesRepository provincesRepository;
	
	public List<ProvincesDTO> findAll() {
		List<ProvincesDTO> itemsDTO = new ArrayList<>();
		provincesRepository.findAll().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
		return itemsDTO;
	}

	public ProvincesDTO findById(String id) {
		return provincesRepository.findById(id).get().convertEntityToDTO();
	}
	
	public boolean existById (String id) {
		if(provincesRepository.existsById(id)) {
			return true;
		}
		return false;
	}
}
