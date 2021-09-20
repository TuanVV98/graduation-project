package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.HistoryWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryWalletRepository extends JpaRepository<HistoryWallet, Long> {
}
