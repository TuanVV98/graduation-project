package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule_time")
public class Schedule_time {
//    id bigint not null primary key auto_increment,
//    day_of_week datetime,
//    start datetime,
//    end datetime,
//    dentist_id bigint,
//    delete_at bit,
//    constraint FK_schedule_time_dentist_profile foreign key(dentist_id) references dentist_profile(id)
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
}
