package com.spring.service.customer;

import com.spring.dto.model.CustomerProfileDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.CustomerProfile;
import com.spring.repository.CustomerProfileRepository;
import com.spring.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerProfileRepository customerRepository;
    private MapperUtil mapperList;
    @Autowired
    public CustomerServiceImpl(CustomerProfileRepository customerRepository, MapperUtil mapperList){
        this.customerRepository = customerRepository;
        this.mapperList = mapperList;
    }
    @Override
    public List<CustomerProfileDTO> getAll() {
        return mapperList.mapList(customerRepository.findAll(), CustomerProfileDTO.class);
    }

    @Override
    public CustomerProfileDTO getById(Long id) {
        CustomerProfileDTO dto = customerRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Customer is not found")).convertEntityToDTO();
        return dto;
    }

    @Override
    public CustomerProfileDTO add(CustomerProfileDTO dto) {
        CustomerProfile profile = dto.convertDTOToEntity();
        return customerRepository.save(profile).convertEntityToDTO();
    }

    @Override
    public CustomerProfileDTO update(Long id, CustomerProfileDTO dto) {
        CustomerProfile profile = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer is not found"));

        return customerRepository.save(profile).convertEntityToDTO();
    }

    @Override
    public void deleteById(Long id) {
        CustomerProfile entity = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer is not found"));
        customerRepository.delete(entity);
    }
}
