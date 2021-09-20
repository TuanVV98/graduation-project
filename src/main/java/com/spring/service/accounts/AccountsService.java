package com.spring.service.accounts;

import com.spring.dto.model.AccountsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountsService {

    public List<AccountsDTO> findAll();

    public AccountsDTO findById(Long id);

    public AccountsDTO save(AccountsDTO dto);

    public void delete(Long id);

}
