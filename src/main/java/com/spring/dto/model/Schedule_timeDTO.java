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
public class Schedule_timeDTO {

    private Integer id;

    private Date dayOfWeek = new Date();

    private Date start = new Date();

    private Date end = new Date();

    Dentist_profileDTO dentist_profile;

    private Boolean deleteAt;

}
