package com.spring.service.accounts;

import com.spring.dto.model.AccountsDTO;
import com.spring.model.Accounts;
import com.spring.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountsServiceImpl implements AccountsService{

    @Autowired
    private AccountsRepository accountRepository;

    @Override
    public List<AccountsDTO> findAll() {
        List<AccountsDTO> itemsDTO = new ArrayList<>();
        accountRepository.findAll().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
        return itemsDTO;
    }

    @Override
    public AccountsDTO findById(Long id) {
        AccountsDTO itemDTO = new AccountsDTO();
        itemDTO = accountRepository.findById(id).get().convertEntityToDTO();
        return itemDTO;
    }

    @Override
    public AccountsDTO register(AccountsDTO accountDTO) {
        Accounts accountEntity = accountDTO.convertDTOToEntity();
        return accountRepository.save(accountEntity).convertEntityToDTO();
    }

    @Override
    public AccountsDTO delete(Long id) {
        Accounts dto = accountRepository.findById(id).get();
        dto.setDeleteAt(true);
        return accountRepository.save(dto).convertEntityToDTO();
    }

    @Override
    public boolean checkIdExist(Long id) {
        return accountRepository.checkIdExist(id);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return accountRepository.checkEmailExist(email);
    }

}
