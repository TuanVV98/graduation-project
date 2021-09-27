package com.spring.service.accounts;

import com.spring.dto.model.AccountsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountsService {

    public List<AccountsDTO> findAll();

    public AccountsDTO findById(Long id);

    public AccountsDTO register(AccountsDTO dto);

    public AccountsDTO delete(Long id);

    public boolean checkIdExist(Long id);

    public boolean checkEmailExist(String email);

}
