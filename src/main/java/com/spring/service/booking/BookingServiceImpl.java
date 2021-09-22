package com.spring.service.booking;

import com.spring.dto.model.BookingDTO;
import com.spring.model.Booking;
import com.spring.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<BookingDTO> findAll() {
        List<BookingDTO> dtoList = new ArrayList<>();
        List<Booking> entityList = bookingRepository.findAll();
        for (Booking entity : entityList){
            BookingDTO dto = entity.convertEntityToDTO();
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public BookingDTO findById(Long id) {
        Optional<Booking> optional = bookingRepository.findById(id);
        if(optional.isPresent()){
            Booking entity = optional.get();
            BookingDTO dto = entity.convertEntityToDTO();
            return dto;
        }
        return null;
    }
}
