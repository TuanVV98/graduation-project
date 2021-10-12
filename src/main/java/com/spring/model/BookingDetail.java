package com.spring.model;

import com.spring.dto.model.BookingDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_detail")
public class BookingDetail implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    Booking booking;

    @ManyToOne
    @JoinColumn(name = "service_id")
    Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "voucher_id",
            nullable = false,
            columnDefinition = "id")
    Voucher voucher;

    @Column(name = "price")
    private Double price;

    public BookingDetailDTO convertEntityToDTO() {
        return new ModelMapper().map(this, BookingDetailDTO.class);
    }

}
