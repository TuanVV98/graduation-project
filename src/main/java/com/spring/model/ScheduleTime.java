package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.RolesDTO;
import com.spring.dto.model.ScheduleTimeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule_time")
public class ScheduleTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "day_of_week")
    private Date dayOfWeek = new Date();

//    @Temporal(TemporalType.TIME)
//    private Date start = new Date();
    @Column(name = "start")
    private  String start;



//    @Temporal(TemporalType.TIME)
//    private Date end = new Date();
    @Column(name = "end")
    private  String end;



    @ManyToOne
    @JoinColumn(name = "dentist_id")
    DentistProfile dentistProfile;

    @Column(name = "delete_at")
    private Boolean deleteAt;

// one to many
    @JsonIgnore
    @OneToMany(mappedBy = "scheduleTime")
    List<Booking> bookings;

    public ScheduleTimeDTO convertEntityToDTO() {
        return new ModelMapper().map(this, ScheduleTimeDTO.class);
    }
}
