package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.AccountsDTO;
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
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updateAt ;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

    @Column(name = "delete_at")
    private Boolean deleteAt;

//one to many

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Comments> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<CustomerProfile> customerProfiles;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<DentistProfile> dentistProfiles;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Likes> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Posts> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<VerificationToken> verificationTokens;

    public AccountsDTO convertEntityToDTO() {
        return new ModelMapper().map(this, AccountsDTO.class);
    }
}