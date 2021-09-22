package com.spring.service.schedule_time;

import com.spring.dto.model.ScheduleTimeDTO;

import java.util.List;

public interface ScheduleTimeService {

    public List<ScheduleTimeDTO> readAll();

    public ScheduleTimeDTO create(ScheduleTimeDTO dto);

    public ScheduleTimeDTO update(ScheduleTimeDTO dto);

    public ScheduleTimeDTO delete(Long id);

    //soft-delete
    public ScheduleTimeDTO updateDeleteAt(Long id,Boolean deleteAt);

    public List<ScheduleTimeDTO> readAllDeleteAtTrue();

    public List<ScheduleTimeDTO> readAllDeleteAtFalse();



}