package com.spring.controller.v1.booking.detail;

import com.spring.dto.model.BookingDetailDTO;
import com.spring.model.BookingDetail;
import com.spring.service.booking.detail.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking/detail")
public class BookingDetailController {
    @Autowired
    private BookingDetailService bookingDetailService;

    //lay bokingdetail theo bookingId
    @GetMapping("/all/{id}") //id la id booking
    public List<BookingDetailDTO> getAllByBookingId(@PathVariable("id") Long id){
        return bookingDetailService.findByBookingId(id);
    }

    @PostMapping()
    public BookingDetailDTO create(@RequestBody BookingDetailDTO bookingDetailDTO){
        return bookingDetailService.create(bookingDetailDTO);
    }

    @PutMapping("{id}")
    public BookingDetailDTO update(@PathVariable("id") Long id, @RequestBody BookingDetailDTO bookingDetailDTO){
        return bookingDetailService.update(bookingDetailDTO);
    }

    @DeleteMapping("{id}")
    public BookingDetailDTO delete(@PathVariable("id") Long id){
        return bookingDetailService.delete(id);
    }

}
