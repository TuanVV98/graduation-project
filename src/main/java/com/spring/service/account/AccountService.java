package com.spring.service.account;

import com.spring.dto.model.AccountsDTO;
import com.spring.model.Accounts;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountsDTO register(AccountsDTO userDTO);

    AccountsDTO update(AccountsDTO userDTO);

    Optional<Accounts> checkIfEmailExistsAndDeletedAt(String email);
    
    Optional<Accounts> checkTelephone(String sdt);
    
    boolean checkId (Long id);

    public List<AccountsDTO> findAll();

    public AccountsDTO findById(Long id);

    public void delete(Long id);
}
