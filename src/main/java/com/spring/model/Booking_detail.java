package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_detail")
public class Booking_detail {
//    id bigint primary key auto_increment,
//    booking_id bigint,
//    service_id bigint,
//    voucher_id varchar(255),
//    price double,
//    constraint FK_booking_detail_service foreign key(service_id) references service(id),
//    constraint FK_booking_detail_voucher foreign key(voucher_id) references voucher(id),
//    constraint FK_booking_detail_booking foreign key(booking_id) references booking(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    Booking booking;

    @ManyToOne
    @JoinColumn(name = "service_id")
    Service service;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;

    @Column(name = "price")
    private Double price;
}
