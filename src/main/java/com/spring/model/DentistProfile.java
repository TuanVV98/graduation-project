package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.DentistProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dentist_profile")
public class DentistProfile implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accounts;

    @Column(name = "image")
    private String image;

    @Column(name = "cccd")
    private String cccd;

    @Column(name = "full_name")
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday = new Date();

    @Column(name = "gender")
    private Boolean gender;

    @ManyToOne
    @JoinColumn(name = "communes_id")
    Communes communes;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "exp")
    private String exp;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updateAt = new Date();

    @Column(name = "delete_at")
    private Boolean deleteAt;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "dentistProfile")
    List<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "dentistProfile")
    List<ScheduleTime> scheduleTimes;

    public DentistProfileDTO convertEntityToDTO() {
        return new ModelMapper().map(this, DentistProfileDTO.class);
    }
}
