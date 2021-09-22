package com.spring.service.booking;

import com.spring.dto.model.BookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> findAll();

    BookingDTO findById(Long id);
}
