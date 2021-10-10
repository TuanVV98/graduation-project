package com.spring.service.districts;

import com.spring.dto.model.DistrictsDTO;

import java.util.List;

public interface DistrictsServices {
    List<DistrictsDTO> findAll();

    DistrictsDTO findById(String id);

    boolean existById (String id);

}
