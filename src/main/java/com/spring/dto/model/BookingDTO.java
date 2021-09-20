package com.spring.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private Integer id;

    Dentist_profileDTO dentist_profile;

    Customer_profileDTO customer_profile;

    private Date bookingDate = new Date();

    private String description;

    private Integer status;

    Schedule_timeDTO schedule_time;
}
