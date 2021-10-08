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

    //get all booking
    @GetMapping()
    public List<BookingDTO> getAll(){
        return bookingService.findAll();
    }

    //get one booking by id
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

    // cập nhật status 0-dang cho 1-dat lich thanh cong 2-dat lich that bai
    @PutMapping("/{id}/status/{status}") // id - booking
    public BookingDTO updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status){
        return bookingService.updateStatus(id, status);
    }

    // - utils
    //lay booking theo scheduletime id (check trùng)
    @GetMapping("/scheduleTime/{id}")
    public BookingDTO checkScheduleTime(@PathVariable("id") Long id){
        return bookingService.findByScheduleTime(id);
    }

    //lấy booking theo acc customer (người dùng xem đặt lịch của mình)
    @GetMapping("/customerId/{id}")
    public List<BookingDTO> getAllByCustomerId(@PathVariable("id") Long id){
        return bookingService.findByCustomerId(id);
    }

    //lấy booking theo acc dentist (bác sĩ xem công việc của mình)
    @GetMapping("/dentistId/{id}")
    public List<BookingDTO> getAllByDentistId(@PathVariable("id") Long id){
        return bookingService.findByDentistId(id);
    }
}