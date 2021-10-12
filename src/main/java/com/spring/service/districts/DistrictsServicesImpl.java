package com.spring.service.districts;

import com.spring.dto.model.DistrictsDTO;
import com.spring.repository.DistrictsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictsServicesImpl implements DistrictsServices{

    private DistrictsRepository districtsRepository;

    @Autowired
    public DistrictsServicesImpl(DistrictsRepository districtsRepository) {
        this.districtsRepository = districtsRepository;
    }

    @Override
    public List<DistrictsDTO> findAll() {
        List<DistrictsDTO> itemsDTO = new ArrayList<>();
        districtsRepository.findAll().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
        return itemsDTO;
    }

    @Override
    public DistrictsDTO findById(String id) {
        return districtsRepository.findById(id).get().convertEntityToDTO();
    }

    @Override
    public boolean existById(String id) {
        if(districtsRepository.existsById(id)) {
            return true;
        }
        return false;
    }
}
