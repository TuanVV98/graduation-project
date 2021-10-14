package com.spring.service.booking;

import com.spring.dto.model.BookingDTO;
import com.spring.dto.model.CustomerProfileDTO;
import com.spring.model.Booking;
import com.spring.repository.BookingRepository;
import com.spring.service.voucher.VoucherServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements  BookingService{

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    VoucherServiceImpl voucherServiceImpl;

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

    @Override
    public BookingDTO create(BookingDTO bookingDTO) {
        Booking entity = bookingDTO.convertDTOToEntity();
        entity.setBookingDate(new Date());
        entity.setStatus(0); //0-dang cho 1-dat lich thanh cong 2-dat lich that bai
        bookingRepository.save(entity);
        bookingDTO.setId(entity.getId());
        return bookingDTO;
    }

    @Override
    public BookingDTO update(BookingDTO bookingDTO) {
        Optional<Booking> optional = bookingRepository.findById(bookingDTO.getId());
        if(optional.isPresent()){
            Booking entity = bookingDTO.convertDTOToEntity();
            bookingRepository.save(entity);
            bookingDTO = entity.convertEntityToDTO();
        }
        return bookingDTO;
    }

    @Override
    public BookingDTO findByScheduleTime(Long id) {
        Optional<Booking> optional = bookingRepository.findByScheduleTimeId(id);
        if(optional.isPresent()){
            Booking entity = optional.get();
            BookingDTO dto = entity.convertEntityToDTO();
            return dto;
        }
        return null;
    }

    @Override
    public List<BookingDTO> findByCustomerId(Long id) {
        List<BookingDTO> dtoList = new ArrayList<>();
        List<Booking> entityList = bookingRepository.findByCustomerId(id);
        for (Booking entity : entityList){
            BookingDTO dto = entity.convertEntityToDTO();
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<BookingDTO> findByDentistId(Long id) {
        List<BookingDTO> dtoList = new ArrayList<>();
        List<Booking> entityList = bookingRepository.findByDentistId(id);
        for (Booking entity : entityList){
            BookingDTO dto = entity.convertEntityToDTO();
            dtoList.add(dto);
        }
        return dtoList;
    }

    // gửi voucher
    @Override
    public BookingDTO updateStatus(Long id, Integer status) {
        Optional<Booking> optional = bookingRepository.findById(id);
        BookingDTO dto = new BookingDTO();
        if(optional.isPresent()){
            Booking entity = optional.get();
            entity.setStatus(status);
            bookingRepository.save(entity);
            dto = entity.convertEntityToDTO();
            if(status == 1) {
            	CustomerProfileDTO b = findById(id).getCustomerProfileDTO();
//            	voucherServiceImpl.sentVoucher(Integer.parseInt(b.getId() + ""), b.getAccountsDTO().getEmail());
            	voucherServiceImpl.sentVoucher(Integer.parseInt(b.getId() + ""), "binhhtph11879@fpt.edu.vn");
            }
        }
        return dto;
    }


}
