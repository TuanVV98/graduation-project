package com.spring.service.account;

import com.spring.dto.model.AccountsDTO;
import com.spring.enumeration.VerificationEnum;
import com.spring.model.Accounts;
import com.spring.model.VerificationToken;
import com.spring.repository.AccountRepository;
import com.spring.repository.VerificationTokenRepository;
import com.spring.service.verificationToken.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final VerificationTokenService verificationTokenService;

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public AccountServiceImpl
            (
                    AccountRepository accountRepository,
                    VerificationTokenService verificationTokenService,
                    VerificationTokenRepository verificationTokenRepository
            ) {
        this.accountRepository = accountRepository;
        this.verificationTokenService = verificationTokenService;
        this.verificationTokenRepository = verificationTokenRepository;
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
    public Optional<Accounts> checkTelephone(String sdt) {
        return this.accountRepository.findByTelephone(sdt);
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

    @Override
    public void sendRegistrationConfirmationEmail(Accounts account) {

        VerificationToken verificationToken = this.verificationTokenService.createVerifiedToken();
        verificationToken.setAccounts(account);
        verificationToken.setType(VerificationEnum.VERIFY_ACCOUNT);

        this.verificationTokenRepository.save(verificationToken);

    }

    @Override
    public void sendResetPasswordEmail(Accounts account) {
        VerificationToken verificationToken = this.verificationTokenService.createResetPasswordToken();
        verificationToken.setAccounts(account);
        verificationToken.setType(VerificationEnum.VERIFY_CHANGE_PASSWORD);

        this.verificationTokenRepository.save(verificationToken);

    }

    @Override
    public boolean verifyAccount(Optional<VerificationToken> verifyToken) {
        LocalDateTime now = LocalDateTime.now();

        if (verifyToken.isPresent() && checkExpiresAt(verifyToken.get().getExpiresAt())) {

            Optional<Accounts> newAccount = this.accountRepository.findById(verifyToken.get().getAccounts().getId());

            this.accountRepository.save(newAccount.get());

            verifyToken.get().setCerified(now);
            this.verificationTokenRepository.save(verifyToken.get());

            return true;
        }

        return false;
    }

    @Override
    public boolean verifyChangePassword(Optional<VerificationToken> checkComfirmToken) {
        LocalDateTime now = LocalDateTime.now();
        if (checkComfirmToken.isPresent() && checkExpiresAt(checkComfirmToken.get().getExpiresAt())) {
            checkComfirmToken.get().setCerified(now);
            this.verificationTokenRepository.save(checkComfirmToken.get());

            return true;
        }

        return false;
    }

    public boolean checkExpiresAt(LocalDateTime expiresAt) {
        LocalDateTime now = LocalDateTime.now();

        if (expiresAt.compareTo(now) == -1) {
            return false;
        }
        return true;
    }

}
