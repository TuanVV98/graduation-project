package com.spring.service.accounts;

import com.spring.dto.model.AccountsDTO;
import com.spring.model.Accounts;
import com.spring.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public AccountsDTO save(AccountsDTO accountDTO) {
        Accounts accountEntity = accountDTO.convertDTOToEntity();
        return accountRepository.save(accountEntity).convertEntityToDTO();
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
