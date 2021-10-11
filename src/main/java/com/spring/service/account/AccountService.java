package com.spring.service.account;

import com.spring.dto.model.AccountsDTO;
import com.spring.model.Accounts;
import com.spring.model.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountsDTO register(AccountsDTO userDTO);

    AccountsDTO update(AccountsDTO userDTO);

    Optional<Accounts> checkIfEmailExistsAndDeletedAt(String email);

    Optional<Accounts> checkTelephone(String sdt);

    List<AccountsDTO> findAll();

    AccountsDTO findById(Long id);

    void delete(Long id);

    void sendRegistrationConfirmationEmail(Accounts account);

    void sendResetPasswordEmail(Accounts account);

    boolean verifyAccount(Optional<VerificationToken> verifyToken);

    boolean verifyChangePassword(Optional<VerificationToken> verifyToken);
}
