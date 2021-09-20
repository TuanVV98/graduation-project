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
    Roles role;

    @Column(name = "delete_at")
    private Boolean deleteAt;

    @JsonIgnore
    @OneToMany(mappedBy = "like")
    List<Likes> like;
}
