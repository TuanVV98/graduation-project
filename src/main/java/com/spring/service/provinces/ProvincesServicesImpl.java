package com.spring.service.provinces;

import com.spring.dto.model.ProvincesDTO;
import com.spring.repository.ProvincesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvincesServicesImpl implements ProvincesServices {

    private ProvincesRepository provincesRepository;

    @Autowired
    public ProvincesServicesImpl(ProvincesRepository provincesRepository) {
        this.provincesRepository = provincesRepository;
    }

    @Override
    public List<ProvincesDTO> findAll() {
        List<ProvincesDTO> itemsDTO = new ArrayList<>();
        provincesRepository.findAll().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
        return itemsDTO;
    }

    @Override
    public ProvincesDTO findById(String id) {
        return provincesRepository.findById(id).get().convertEntityToDTO();
    }

    @Override
    public boolean existById(String id) {
        if(provincesRepository.existsById(id)) {
            return true;
        }
        return false;
    }
}
