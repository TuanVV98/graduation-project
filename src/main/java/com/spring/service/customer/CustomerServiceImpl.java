package com.spring.service.customer;

import com.spring.dto.model.CustomerProfileDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.Accounts;
import com.spring.model.CustomerProfile;
import com.spring.repository.AccountRepository;
import com.spring.repository.CustomerProfileRepository;
import com.spring.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerProfileRepository customerRepository;
    private final MapperUtil mapperList;
    private final AccountRepository accountRepository;
    @Autowired
    public CustomerServiceImpl(CustomerProfileRepository customerRepository, MapperUtil mapperList, AccountRepository accountRepository){
        this.customerRepository = customerRepository;
        this.mapperList = mapperList;
        this.accountRepository = accountRepository;
    }
    @Override
    public List<CustomerProfileDTO> getAll() {
        return mapperList.mapList(customerRepository.findByDeleteAtIsFalse(), CustomerProfileDTO.class);
    }

    @Override
    public List<CustomerProfileDTO> getAllOnRecycleBin() {
        return mapperList.mapList(customerRepository.findByDeleteAtIsTrue(), CustomerProfileDTO.class);
    }

    @Override
    public CustomerProfileDTO getById(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Customer is not found")).convertEntityToDTO();
    }

    @Override
    public CustomerProfileDTO add(CustomerProfileDTO dto) {
        CustomerProfile profile = dto.convertDTOToEntity();
        Accounts accounts = accountRepository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("Accounts is not found"));
        profile.setAccounts(accounts);
        return customerRepository.save(profile).convertEntityToDTO();
    }

    @Override
    public CustomerProfileDTO update(Long id, CustomerProfileDTO dto) {
        CustomerProfile profile = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer is not found"));
            dto.setId(profile.getId());
            CustomerProfile entity = dto.convertDTOToEntity();
            return customerRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public void updateDeleted(Long id, Boolean deleted) {
        CustomerProfile entity = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer is not found"));
            entity.setDeleteAt(deleted);
            customerRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        CustomerProfile entity = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer is not found"));
        customerRepository.deleteById(id);
    }
}
