package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, String> {
}
