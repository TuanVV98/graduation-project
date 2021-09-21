package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.CustomerProfileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "customer_profile")
@ApiModel(value = "Customer model")
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated Customer ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accounts;

    @Column(name = "image")
    private String image;

    @Column(name = "fullname")
    private String fullname;

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

    @Column(name = "story")
    private String story;

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
    @OneToMany(mappedBy = "customerProfile")
    List<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "customerProfile")
    List<EWallet> eWallets;

    public CustomerProfileDTO convertEntityToDTO() {
        return new ModelMapper().map(this, CustomerProfileDTO.class);
    }
}
