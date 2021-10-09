package com.spring.repository;

import com.spring.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {

    Page<Voucher> findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual
            (LocalDateTime startDate, LocalDateTime endDate, Pageable pg);
}
