package com.spring.dto.model;

import com.spring.model.ScheduleTime;
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
public class ScheduleTimeDTO {

    private Long id;

    private Date dayOfWeek = new Date();

    private Date start = new Date();

    private Date end = new Date();

    @NotBlank
    DentistProfileDTO dentistProfile;

    private Boolean deleteAt;

    public ScheduleTime convertDTOToEntity() {
        return new ModelMapper().map(this, ScheduleTime.class);
    }

}
