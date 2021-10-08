package com.spring.service.account;

import com.spring.dto.model.AccountsDTO;
import com.spring.model.Accounts;
import com.spring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * DeleteAt :
     * false : active
     * true : blocked
     *
     * @param userDTO
     * @return
     */
    @Override
    public AccountsDTO register(AccountsDTO userDTO) {
        Accounts entity = userDTO.convertDTOToEntity();
        entity.setDeleteAt(true);
        return this.accountRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public AccountsDTO update(AccountsDTO userDTO) {
        Accounts entity = userDTO.convertDTOToEntity();
        return this.accountRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public Optional<Accounts> checkIfEmailExistsAndDeletedAt(String email) {
        return this.accountRepository.checkIfEmailExistsAndDeletedAt(email);
    }

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
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
