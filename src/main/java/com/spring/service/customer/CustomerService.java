package com.spring.service.customer;

import com.spring.dto.model.CustomerProfileDTO;
import com.spring.exception.NotFoundException;

import java.util.List;

public interface CustomerService {
    List<CustomerProfileDTO> getAll();

    List<CustomerProfileDTO> getAllOnRecycleBin();

    CustomerProfileDTO getById(Long id) throws NotFoundException;

    CustomerProfileDTO add(CustomerProfileDTO dto) throws NotFoundException;

    CustomerProfileDTO update(Long id, CustomerProfileDTO dto) throws NotFoundException;

    void updateDeleted(Long id, Boolean deleted) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException ;
}
