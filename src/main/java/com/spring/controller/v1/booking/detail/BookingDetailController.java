package com.spring.controller.v1.booking.detail;

import com.spring.dto.model.BookingDTO;
import com.spring.dto.model.BookingDetailDTO;
import com.spring.dto.model.LikesDTO;
import com.spring.dto.response.Response;
import com.spring.model.BookingDetail;
import com.spring.service.booking.detail.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking/detail")
public class BookingDetailController {
    @Autowired
    private BookingDetailService bookingDetailService;

    //lay bokingdetail theo bookingId
    @GetMapping("/all/{id}") //id la id booking
    public ResponseEntity<Response<List<BookingDetailDTO>>> getAllByBookingId(@PathVariable("id") Long id){
        Response<List<BookingDetailDTO>> response= new Response<>();
        response.setData(bookingDetailService.findByBookingId(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response<BookingDetailDTO>> create(@RequestBody @Valid BookingDetailDTO bookingDetailDTO, BindingResult bindingResult){
        Response<BookingDetailDTO> response= new Response<>();
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().
                    forEach(error->response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(bookingDetailService.create(bookingDetailDTO));
        return new ResponseEntity<>(response,HttpStatus.CREATED);


    }

    @PutMapping("{id}")
    public ResponseEntity<Response<BookingDetailDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid BookingDetailDTO bookingDetailDTO, BindingResult bindingResult){
        Response<BookingDetailDTO> response= new Response<>();
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().
                    forEach(error->response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(bookingDetailService.update(bookingDetailDTO));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<BookingDetailDTO>> delete(@PathVariable("id") Long id){
        Response<BookingDetailDTO> response= new Response<>();
        response.setData(bookingDetailService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
