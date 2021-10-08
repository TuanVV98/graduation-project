package com.spring.service.services;

import com.spring.dto.model.ServiceDTO;

import java.util.List;

public interface ServicesService {
    List<ServiceDTO> findAll();

    ServiceDTO findById(Long id);

    ServiceDTO findByName(String name);

    ServiceDTO create(ServiceDTO DTO);

    ServiceDTO update(ServiceDTO DTO);

    ServiceDTO delete(ServiceDTO DTO);

    boolean existById(Long id);

    boolean existByName(String name);
}
