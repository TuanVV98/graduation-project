package com.spring.service.booking.detail;

import com.spring.dto.model.BookingDetailDTO;

import java.util.List;

public interface BookingDetailService {

    List<BookingDetailDTO> findByBookingId(Long id);

    BookingDetailDTO create(BookingDetailDTO bookingDetailDTO);

    BookingDetailDTO update(BookingDetailDTO bookingDetailDTO);

    BookingDetailDTO delete(Long id);
}
