package com.spring.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking_detailDTO {

    private Integer id;

    BookingDTO booking;

    ServiceDTO service;

    VoucherDTO voucher;

    private Double price;

}
