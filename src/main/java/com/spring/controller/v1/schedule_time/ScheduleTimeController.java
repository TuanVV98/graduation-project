package com.spring.controller.v1.schedule_time;

import com.spring.dto.model.ScheduleTimeDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotParsableContentException;
import com.spring.service.schedule_time.ScheduleTimeService;
import com.spring.utils.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule-time")
public class ScheduleTimeController {
    private ScheduleTimeService scheduleTimeService;

    @Autowired
    public ScheduleTimeController(ScheduleTimeService scheduleTimeService) {
        this.scheduleTimeService = scheduleTimeService;
    }

    //read all schedule-time
    @GetMapping("/all")
    public ResponseEntity<Response<List<ScheduleTimeDTO>>> readAll() {
        Response<List<ScheduleTimeDTO>> response = new Response<>();
        response.setData(this.scheduleTimeService.readAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response<ScheduleTimeDTO>> create(
            @RequestBody @Valid ScheduleTimeDTO dto,
            BindingResult result
    ) throws NotParsableContentException {

        System.out.println("start : "+dto.getStart());
        System.out.println("end : "+dto.getEnd());
        System.out.println("dayofweek : "+dto.getDayOfWeek());
        Response<ScheduleTimeDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors()
                    .forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        if(!ApiUtil.isEndDateGreaterThanStartDate(dto.getStart(), dto.getEnd())){
            throw new NotParsableContentException("Ngày bắt đầu schedule time lớn hơn nhày kết thúc voucher");
        }
        response.setData(this.scheduleTimeService.create(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ScheduleTimeDTO>> update(
            @RequestBody @Valid ScheduleTimeDTO dto,
            BindingResult result
    ) throws NotParsableContentException {
        Response<ScheduleTimeDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors()
                    .forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        if(!ApiUtil.isEndDateGreaterThanStartDate(dto.getStart(), dto.getEnd())){
            throw new NotParsableContentException("Ngày bắt đầu schedule time lớn hơn nhày kết thúc voucher");
        }
        response.setData(this.scheduleTimeService.update(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<ScheduleTimeDTO>> delete(
            @PathVariable Long id
    ) {
        Response<ScheduleTimeDTO> response = new Response<>();
        response.setData(this.scheduleTimeService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //soft-delete
    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<Response<ScheduleTimeDTO>> soft_delete(
            @PathVariable Long id,
            @RequestParam Boolean deleteAt
    ) {
        Response<ScheduleTimeDTO> response = new Response<>();
        response.setData(this.scheduleTimeService.updateDeleteAt(id, deleteAt));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //read all by deleteAt=TRUE
    @GetMapping("/recycle-bin")
    public ResponseEntity<Response<List<ScheduleTimeDTO>>> getAllDeleteAtTrue() {
        Response<List<ScheduleTimeDTO>> response = new Response<>();
        response.setData(this.scheduleTimeService.readAllDeleteAtTrue());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //read all by deleteAt=FALSE
    @GetMapping()
    public ResponseEntity<Response<List<ScheduleTimeDTO>>> getAllDeleteAtFalse() {
        Response<List<ScheduleTimeDTO>> response = new Response<>();
        response.setData(this.scheduleTimeService.readAllDeleteAtFalse());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //read all schedule_time with dentist_id
    @GetMapping("/dentist")
    public ResponseEntity<Response<List<ScheduleTimeDTO>>> getAllTimeByDentistId(
            @RequestParam Long dentistProfileId
    ) {
        Response<List<ScheduleTimeDTO>> response = new Response<>();
        response.setData(this.scheduleTimeService.readAllTimeByDentistId(dentistProfileId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}