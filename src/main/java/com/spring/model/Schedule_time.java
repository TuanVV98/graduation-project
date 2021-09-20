package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule_time")
public class Schedule_time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "day_of_week")
    private Date dayOfWeek = new Date();

    @Temporal(TemporalType.TIME)
    @Column(name = "start")
    private Date start = new Date();

    @Temporal(TemporalType.TIME)
    @Column(name = "end")
    private Date end = new Date();

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    Dentist_profile dentist_profile;

    @Column(name = "delete_at")
    private Boolean deleteAt;

// one to many
    @JsonIgnore
    @OneToMany(mappedBy = "schedule_time")
    List<Booking> bookings;
}
