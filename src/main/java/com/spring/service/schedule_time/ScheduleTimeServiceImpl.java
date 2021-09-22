package com.spring.service.schedule_time;

import com.spring.dto.model.ScheduleTimeDTO;
import com.spring.repository.ScheduleTimeRepository;
import com.spring.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTimeServiceImpl implements ScheduleTimeService{


    private ScheduleTimeRepository scheduleTimeRepo;
    private MapperUtil mapperUtil;

    @Autowired
    public ScheduleTimeServiceImpl(
            ScheduleTimeRepository scheduleTimeRepo,
            MapperUtil mapperUtil
            ){
        this.scheduleTimeRepo=scheduleTimeRepo;
        this.mapperUtil=mapperUtil;

    }



    @Override
    public List<ScheduleTimeDTO> readAll() {
        return null;
    }

    @Override
    public ScheduleTimeDTO create(ScheduleTimeDTO dto) {
        return null;
    }

    @Override
    public ScheduleTimeDTO update(ScheduleTimeDTO dto) {
        return null;
    }

    @Override
    public ScheduleTimeDTO delete(Long id) {
        return null;
    }

    // soft -delete
    @Override
    public ScheduleTimeDTO updateDeleteAt(Long id, Boolean deleteAt) {
        return null;
    }

    @Override
    public List<ScheduleTimeDTO> readAllDeleteAtTrue() {
        return null;
    }

    @Override
    public List<ScheduleTimeDTO> readAllDeleteAtFalse() {
        return null;
    }
}
