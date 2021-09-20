package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.VerifycationTokenDTO;
import com.spring.dto.model.VoucherDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "sale")
    private Double sale;

    @Temporal(TemporalType.DATE)
    @Column(name = "start")
    private Date start = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "end")
    private Date end = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @Column(name = "delete_at")
    private Boolean deleteAt;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    List<BookingDetail> bookingDetails;

    public VoucherDTO convertEntityToDTO() {
        return new ModelMapper().map(this, VoucherDTO.class);
    }
}