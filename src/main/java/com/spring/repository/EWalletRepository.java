package com.spring.repository;

import com.spring.model.Districts;
import com.spring.model.EWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EWalletRepository extends JpaRepository<EWallet, Long> {
}
