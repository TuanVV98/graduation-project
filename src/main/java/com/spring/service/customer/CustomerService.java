package com.spring.service.customer;

import com.spring.dto.model.CustomerProfileDTO;
import com.spring.model.CustomerProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerProfileDTO> getAll();
    CustomerProfileDTO getById(Long id);
    CustomerProfileDTO add(CustomerProfileDTO dto);
    CustomerProfileDTO update(Long id, CustomerProfileDTO dto);
    void deleteById(Long id);
}
