package com.spring.dto.model;

import com.spring.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private Long id;

    @NotNull
    DentistProfileDTO dentistProfile;

    @NotNull
    CustomerProfileDTO customerProfile;

    private Date bookingDate = new Date();

    private String description;

    @NotNull
    private Integer status;

    @NotNull
    ScheduleTimeDTO scheduleTime;

    public Booking convertDTOToEntity() {
        return new ModelMapper().map(this, Booking.class);
    }

}
