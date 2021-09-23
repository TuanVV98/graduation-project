package com.spring.service.schedule_time;

import com.spring.dto.model.ScheduleTimeDTO;
import com.spring.model.ScheduleTime;
import com.spring.repository.ScheduleTimeRepository;
import com.spring.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return this.mapperUtil.mapList(this.scheduleTimeRepo.findAll(),ScheduleTimeDTO.class);
    }

    @Override
    public ScheduleTimeDTO create(ScheduleTimeDTO dto) {
        ScheduleTime entity=dto.convertDTOToEntity();
        entity.setDeleteAt(false);
        this.scheduleTimeRepo.save(entity);
        return entity.convertEntityToDTO();
    }

    @Override
    public ScheduleTimeDTO update(ScheduleTimeDTO dto) {
        Optional<ScheduleTime> optional=this.scheduleTimeRepo.findById(dto.getId());
        if(optional.isPresent()){
            ScheduleTime entity=dto.convertDTOToEntity();
            entity.setDeleteAt(false);
            this.scheduleTimeRepo.save(entity);
            return entity.convertEntityToDTO();
        }
        return null;
    }

    @Override
    public ScheduleTimeDTO delete(Long id) {
        Optional<ScheduleTime> optional=this.scheduleTimeRepo.findById(id);
        if(optional.isPresent()){
            ScheduleTime entity=optional.get();
            ScheduleTimeDTO dto=entity.convertEntityToDTO();
            this.scheduleTimeRepo.delete(entity);
            return dto;
        }
        return null;
    }

    // soft -delete
    @Override
    public ScheduleTimeDTO updateDeleteAt(Long id, Boolean deleteAt) {
        Optional<ScheduleTime> optional=this.scheduleTimeRepo.findById(id);
        if(optional.isPresent()){
            ScheduleTime entity=optional.get();
            entity.setDeleteAt(deleteAt);
            this.scheduleTimeRepo.save(entity);
            return entity.convertEntityToDTO();
        }
        return null;
    }

    //read all schedule_time by deleteAt=TRUE(Recycle_Bin)
    @Override
    public List<ScheduleTimeDTO> readAllDeleteAtTrue() {
        return this.mapperUtil.mapList(this.scheduleTimeRepo.findByDeleteAtIsTrue()
                ,ScheduleTimeDTO.class);
    }

    //read all schedule_time by deleteAt=FALSE
    @Override
    public List<ScheduleTimeDTO> readAllDeleteAtFalse() {
        return this.mapperUtil.mapList(this.scheduleTimeRepo.findByDeleteAtIsFalse()
                ,ScheduleTimeDTO.class);
    }

    //read all schedule_time by DentistProfile_ID
    @Override
    public List<ScheduleTimeDTO> readAllTimeByDentistId(Long dentistProfileId) {
        return this.mapperUtil.mapList(this.scheduleTimeRepo.findAllTimeByDentistId(dentistProfileId)
                ,ScheduleTimeDTO.class);
    }


}
