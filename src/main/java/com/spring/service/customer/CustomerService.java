package com.spring.service.customer;

import com.spring.dto.model.CustomerProfileDTO;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface CustomerService {
    List<CustomerProfileDTO> getAll();
    List<CustomerProfileDTO> getAllOnRecycleBin();
    CustomerProfileDTO getById(Long id);
    CustomerProfileDTO add(CustomerProfileDTO dto);
    CustomerProfileDTO update(Long id, CustomerProfileDTO dto);
    void updateDeleted(Long id, Boolean deleted);
    void deleteById(Long id);
}
