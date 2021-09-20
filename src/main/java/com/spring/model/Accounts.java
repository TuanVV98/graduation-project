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
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updateAt = new Date();

    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles roles;

    @Column(name = "delete_at")
    private Boolean deleteAt;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Likes> like;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Comments> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Customer_profile> customer_profiles;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Dentist_profile> dentist_profiles;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Likes> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Posts> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Verifycation_token> verifycation_tokens;
}
