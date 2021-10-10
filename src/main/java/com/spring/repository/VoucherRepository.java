package com.spring.repository;

import com.spring.dto.model.VoucherDTO;
import com.spring.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {

    List<Voucher> findAllByStartGreaterThanEqualAndStartLessThanEqual
            (LocalDateTime startDate, LocalDateTime endDate);

    List<Voucher> findByContent(String title);
}
