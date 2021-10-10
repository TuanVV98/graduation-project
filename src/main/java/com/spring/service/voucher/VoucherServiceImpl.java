package com.spring.service.voucher;

import com.spring.dto.model.VoucherDTO;
import com.spring.model.Posts;
import com.spring.model.Voucher;
import com.spring.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService{

    private final VoucherRepository voucherRepository;

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
        List<VoucherDTO> itemDTO = new ArrayList<>();
        this.voucherRepository.findByContent(title).forEach(t ->itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;

    }

    @Override
    public Optional<VoucherDTO> findById(String id) {

        Optional<Voucher> voucher = this.voucherRepository.findById(id);
        if(voucher.isPresent()){
            return voucher.map(Voucher::convertEntityToDTO);
        }
        return Optional.empty();
    }

    @Override
    public List<VoucherDTO> findAll() {
        List<VoucherDTO> itemDTO = new ArrayList<>();
        this.voucherRepository.findAll().forEach(t ->itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    @Override
    public List<Voucher> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
       return this.voucherRepository
                .findAllByStartGreaterThanEqualAndStartLessThanEqual(startDate, endDate);
    }
}
