package com.spring.service.account;

import com.spring.dto.model.AccountsDTO;
import com.spring.model.Accounts;
import com.spring.model.VerificationToken;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountsDTO register(AccountsDTO userDTO) throws MessagingException;

    AccountsDTO updatePassword(AccountsDTO userDTO) throws MessagingException;

    Optional<Accounts> checkIfEmailExistsAndDeletedAt(String email);

    Optional<Accounts> checkTelephone(String sdt);

    List<AccountsDTO> findAll();

    AccountsDTO findById(Long id);

    void delete(Long id);

    void sendRegistrationConfirmationEmail(Accounts account) throws MessagingException;

    void sendResetPasswordEmail(Accounts account) throws MessagingException;

    boolean verifyAccount(Optional<VerificationToken> verifyToken);

    boolean verifyChangePassword(Optional<VerificationToken> verifyToken);
}
