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

    DentistProfileDTO dentistProfileDTO;

    CustomerProfileDTO customerProfileDTO;

    private Date bookingDate = new Date();

    private String description;

    private Integer status;

    ScheduleTimeDTO schedule_time;
}
