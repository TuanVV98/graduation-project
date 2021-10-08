package com.spring.service.booking.detail;

import com.spring.dto.model.BookingDetailDTO;
import com.spring.model.BookingDetail;
import com.spring.repository.BookingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingDetailServiceImpl implements BookingDetailService{

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    @Override
    public List<BookingDetailDTO> findByBookingId(Long id) {
        List<BookingDetailDTO> dtoList = new ArrayList<>();
        List<BookingDetail> entityList = bookingDetailRepository.findBookingDetailByBookingId(id);
        for (BookingDetail entity : entityList){
            BookingDetailDTO dto = entity.convertEntityToDTO();
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public BookingDetailDTO create(BookingDetailDTO bookingDetailDTO) {
        BookingDetail entity = bookingDetailDTO.convertDTOToEntity();
        bookingDetailRepository.save(entity);
        bookingDetailDTO.setId(entity.getId());
        return bookingDetailDTO;
    }

    @Override
    public BookingDetailDTO update(BookingDetailDTO bookingDetailDTO) {
        Optional<BookingDetail> optional = bookingDetailRepository.findById(bookingDetailDTO.getId());
        if(optional.isPresent()){
            BookingDetail entity = bookingDetailDTO.convertDTOToEntity();
            bookingDetailRepository.save(entity);
            bookingDetailDTO = entity.convertEntityToDTO();
        }
        return bookingDetailDTO;
    }

    @Override
    public BookingDetailDTO delete(Long id) {
        Optional<BookingDetail> optional = bookingDetailRepository.findById(id);
        if(optional.isPresent()){
            BookingDetail entity = optional.get();
            BookingDetailDTO dto = entity.convertEntityToDTO();
            bookingDetailRepository.delete(entity);
            return dto;
        }
        return null;
    }
}
