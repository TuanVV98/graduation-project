package com.spring.service.districts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.DistrictsDTO;
import com.spring.repository.DistrictsRepository;

@Service
public class DistrictsServices {
	@Autowired
	DistrictsRepository districtsRepository;
	
	public List<DistrictsDTO> findAll() {
		List<DistrictsDTO> itemsDTO = new ArrayList<>();
		districtsRepository.findAll().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
		return itemsDTO;
	}

	public DistrictsDTO findById(String id) {
		return districtsRepository.findById(id).get().convertEntityToDTO();
	}
	
	public boolean existById (String id) {
		if(districtsRepository.existsById(id)) {
			return true;
		}
		return false;
	}
}
