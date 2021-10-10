package com.spring.service.verificationToken;

import com.spring.model.VerificationToken;
import com.spring.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
public class VerificationTokenServiceImpl implements VerificationTokenService{

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");

    private final VerificationTokenRepository verificationTokenRepository;

    @Value("${jdj.secure.token.validity}")
    private int tokenValidityInSeconds;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken createVerifiedToken() {

        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(tokenValue);
        verificationToken.setExpiresAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
        return verificationToken;
    }

    @Override
    public VerificationToken createResetPasswordToken() {
        Random rd = new Random();
        int a = rd.nextInt(9999999);

        String tokenValue =  String.valueOf(a);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(tokenValue);
        verificationToken.setExpiresAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));

        return verificationToken;
    }

    @Override
    public void saveVerifiedToken(VerificationToken token) {

        this.verificationTokenRepository.save(token);
    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return this.verificationTokenRepository.findByTokenAndCerifiedIsNull(token);
    }

    @Override
    public Optional<VerificationToken> checkComfirmToken(String token) {
        return this.verificationTokenRepository.findByTokenAndCerifiedIsNull(token);
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }
}
