package com.spring.controller.v1.booking;

import com.spring.dto.model.BookingDTO;
import com.spring.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public List<BookingDTO> getAll(){
        return bookingService.findAll();
    }

    @GetMapping("{id}")
    public BookingDTO getOne(@PathVariable("id") Long id){
        return bookingService.findById(id);
    }
}
