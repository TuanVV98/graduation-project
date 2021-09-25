package com.spring.service.booking;

import com.spring.dto.model.BookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> findAll();

    BookingDTO findById(Long id);

    BookingDTO create(BookingDTO bookingDTO);

    BookingDTO update(BookingDTO bookingDTO);

    BookingDTO findByScheduleTime(Long id);
}
