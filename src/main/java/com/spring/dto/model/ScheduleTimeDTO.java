package com.spring.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.model.ScheduleTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleTimeDTO {


    private Long id;

    @NotNull(message = "Không để trống dayOfWeek")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dayOfWeek ;

    @NotNull(message = "Không để trống start !")
//    @Pattern(regexp = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$",
//            message = "Vui lòng nhập đúng định dạng giờ HH:mm")
    private LocalDateTime start;

    @NotNull(message = "Không để trống end !")
//    @Pattern(regexp = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$",
//            message = "Vui lòng nhập đúng định dạng giờ HH:mm")
    private  LocalDateTime end;


    @NotNull(message = "Không để trống Id của nha sĩ !")
    private Long dentistProfileId;

    private Boolean deleteAt;

    public ScheduleTime convertDTOToEntity() {
        return new ModelMapper().map(this, ScheduleTime.class);
    }

}
