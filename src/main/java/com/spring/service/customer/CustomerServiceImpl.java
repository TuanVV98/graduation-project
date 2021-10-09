package com.spring.service.customer;

import com.spring.dto.model.CustomerProfileDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.Accounts;
import com.spring.model.CustomerProfile;
import com.spring.repository.AccountRepository;
import com.spring.repository.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerProfileRepository customerRepository;

    private final AccountRepository accountRepository;
    @Autowired
    public CustomerServiceImpl(CustomerProfileRepository customerRepository, AccountRepository accountRepository){
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }
    @Override
    public List<CustomerProfileDTO> getAll() {
        List<CustomerProfileDTO> itemDTO = new ArrayList<>();
        this.customerRepository.findByDeleteAtIsFalse().forEach(t->itemDTO.add(t.convertEntityToDTO()));

        return itemDTO;
    }

    @Override
    public List<CustomerProfileDTO> getAllOnRecycleBin() {
        List<CustomerProfileDTO> itemDTO = new ArrayList<>();
        this.customerRepository.findByDeleteAtIsTrue().forEach(t->itemDTO.add(t.convertEntityToDTO()));

        return itemDTO;
    }

    @Override
    public CustomerProfileDTO getById(Long id) throws NotFoundException {
        return customerRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Customer is not found")).convertEntityToDTO();
    }

    @Override
    public CustomerProfileDTO add(CustomerProfileDTO dto) throws NotFoundException {
        CustomerProfile profile = dto.convertDTOToEntity();
        Accounts accounts = accountRepository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("Accounts is not found"));
        profile.setAccounts(accounts);
        return customerRepository.save(profile).convertEntityToDTO();
    }

    @Override
    public CustomerProfileDTO update(Long id, CustomerProfileDTO dto) throws NotFoundException  {
        CustomerProfile profile = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer is not found"));
        dto.setId(profile.getId());
        CustomerProfile entity = dto.convertDTOToEntity();
        return customerRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public void updateDeleted(Long id, Boolean deleted) throws NotFoundException {
        CustomerProfile entity = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer is not found"));
        entity.setDeleteAt(deleted);
        customerRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        CustomerProfile entity = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer is not found"));
        customerRepository.deleteById(id);
    }

}
