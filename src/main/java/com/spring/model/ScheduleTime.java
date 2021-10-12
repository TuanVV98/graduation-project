package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.ScheduleTimeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule_time")
public class ScheduleTime implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "day_of_week")
    private Date dayOfWeek ;

//    @Temporal(TemporalType.TIME)
    @Column(name = "start")
    private LocalDateTime start ;

//    @Temporal(TemporalType.TIME)
    @Column(name = "end")
    private LocalDateTime end ;

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
