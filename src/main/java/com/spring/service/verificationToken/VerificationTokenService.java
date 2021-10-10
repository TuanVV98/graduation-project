package com.spring.service.verificationToken;

import com.spring.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {

    VerificationToken createVerifiedToken();

    VerificationToken createResetPasswordToken();

    void saveVerifiedToken(VerificationToken token);

    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> checkComfirmToken(String token);
}
