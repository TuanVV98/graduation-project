package com.spring.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailDTO {

    private Integer id;

    BookingDTO bookingDTO;

    ServiceDTO serviceDTO;

    VoucherDTO voucherDTO;

    private Double price;

}
