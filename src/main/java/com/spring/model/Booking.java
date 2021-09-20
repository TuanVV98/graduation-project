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
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    Dentist_profile dentist_profile;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer_profile customer_profile;

    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date")
    private Date bookingDate = new Date();

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "schedule_time_id")
    Schedule_time schedule_time;
}
