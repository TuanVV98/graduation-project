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
public class ScheduleTimeDTO {

    private Integer id;

    private Date dayOfWeek = new Date();

    private Date start = new Date();

    private Date end = new Date();

    DentistProfileDTO dentistProfileDTO;

    private Boolean deleteAt;

}
