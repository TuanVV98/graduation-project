package com.spring.service.provinces;

import com.spring.dto.model.ProvincesDTO;

import java.util.List;

public interface ProvincesServices {
    List<ProvincesDTO> findAll();

    ProvincesDTO findById(String id);

    boolean existById (String id);
}
