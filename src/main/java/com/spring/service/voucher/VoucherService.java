package com.spring.service.voucher;

import com.spring.dto.model.VoucherDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface VoucherService {

    VoucherDTO save(VoucherDTO dto);

    VoucherDTO update(VoucherDTO dto);

    List<VoucherDTO> findByTitle(String title);

    Optional<VoucherDTO> findById(String id);

    List<VoucherDTO> findAll();

    List<Voucher> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    void hardDelete(String id) throws NotFoundException;
}
