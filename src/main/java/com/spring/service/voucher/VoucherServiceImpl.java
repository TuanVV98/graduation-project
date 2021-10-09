package com.spring.service.voucher;

import com.spring.dto.model.VoucherDTO;
import com.spring.model.Voucher;
import com.spring.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService{

    private VoucherRepository voucherRepository;

    @Autowired
    public VoucherServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public VoucherDTO save(VoucherDTO dto) {
        return this.voucherRepository.save(dto.convertDTOToEntity()).convertEntityToDTO();
    }

    @Override
    public VoucherDTO update(VoucherDTO dto) {
        return this.voucherRepository.save(dto.convertDTOToEntity()).convertEntityToDTO();
    }

    @Override
    public List<VoucherDTO> findByTitle(String title) {
        return null;
    }

    @Override
    public Optional<VoucherDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<VoucherDTO> findAll() {
        return null;
    }

    @Override
    public Page<Voucher> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return this.voucherRepository
                .findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual(startDate, endDate, pageable);
    }
}
