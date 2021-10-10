package com.spring.service.communes;

import com.spring.dto.model.CommunesDTO;

import java.util.List;

public interface CommunesServices {

    List<CommunesDTO> findAll();

    CommunesDTO findById(String id);

    boolean existById (String id);
}
