package com.spring.model;

import com.spring.dto.model.VoucherDTO;
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
@Table(name = "voucher")
public class Voucher implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "sale")
    private Double sale;

//    @Temporal(TemporalType.DATE)
    @Column(name = "start")
    private LocalDateTime start ;

//    @Temporal(TemporalType.DATE)
    @Column(name = "end")
    private LocalDateTime  end ;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @Column(name = "delete_at")
    private Boolean deleteAt;

    @OneToMany(
            mappedBy = "voucher",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BookingDetail> bookingDetails;

    public VoucherDTO convertEntityToDTO() {
        return new ModelMapper().map(this, VoucherDTO.class);
    }
}
