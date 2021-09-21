package com.spring.repository;

import com.spring.model.VerifycationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifycationTokenRepository extends JpaRepository<VerifycationToken, Long> {
}
