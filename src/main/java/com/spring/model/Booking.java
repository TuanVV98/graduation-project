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
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    DentistProfile dentistProfile;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerProfile customerProfile;

    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date")
    private Date bookingDate = new Date();

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "schedule_time_id")
    ScheduleTime scheduleTime;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    List<BookingDetail> bookingDetails;
}
