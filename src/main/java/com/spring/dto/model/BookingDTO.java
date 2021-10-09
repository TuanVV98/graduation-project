package com.spring.dto.model;

import com.spring.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

   
    private Long id;

    @NotBlank
    DentistProfileDTO dentistProfileDTO;

    @NotBlank
    CustomerProfileDTO customerProfileDTO;

    private Date bookingDate = new Date();

    private String description;

    @NotNull
    private Integer status;

    @NotBlank
    ScheduleTimeDTO schedule_time;

    public Booking convertDTOToEntity() {
        return new ModelMapper().map(this, Booking.class);
    }

}
