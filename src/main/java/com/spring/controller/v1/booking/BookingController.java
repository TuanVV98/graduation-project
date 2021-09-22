package com.spring.controller.v1.booking;

import com.spring.dto.model.BookingDTO;
import com.spring.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public BookingDTO create(@RequestBody BookingDTO bookingDTO){
        return bookingService.create(bookingDTO);
    }

    @PutMapping("{id}")
    public BookingDTO update(@PathVariable("id") Long id, @RequestBody BookingDTO bookingDTO){
        return bookingService.update(bookingDTO);
    }
}
