package com.spring.service.schedule_time;

import com.spring.dto.model.ScheduleTimeDTO;
import com.spring.model.ScheduleTime;
import com.spring.repository.ScheduleTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleTimeServiceImpl implements ScheduleTimeService{

    private ScheduleTimeRepository scheduleTimeRepo;

    @Autowired
    public ScheduleTimeServiceImpl(ScheduleTimeRepository scheduleTimeRepo){
        this.scheduleTimeRepo=scheduleTimeRepo;


    }



    @Override
    public List<ScheduleTimeDTO> readAll() {
        List<ScheduleTimeDTO> itemDTO = new ArrayList<>();
        this.scheduleTimeRepo.findAll().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    @Override
    public ScheduleTimeDTO create(ScheduleTimeDTO dto) {
        ScheduleTime entity=dto.convertDTOToEntity();
        System.out.println("****************************");
        System.out.println("end :"+entity.getEnd());
        System.out.println("start :"+entity.getStart());
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
        List<ScheduleTimeDTO> itemDTO = new ArrayList<>();
        this.scheduleTimeRepo.findByDeleteAtIsTrue().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    //read all schedule_time by deleteAt=FALSE
    @Override
    public List<ScheduleTimeDTO> readAllDeleteAtFalse() {
        List<ScheduleTimeDTO> itemDTO = new ArrayList<>();
        this.scheduleTimeRepo.findByDeleteAtIsFalse().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    //read all schedule_time by DentistProfile_ID
    @Override
    public List<ScheduleTimeDTO> readAllTimeByDentistId(Long dentistProfileId) {
        List<ScheduleTimeDTO> itemDTO = new ArrayList<>();
        this.scheduleTimeRepo.findAllTimeByDentinstId(dentistProfileId).forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

}
