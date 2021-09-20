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
@Table(name = "dentist_profile")
public class Dentist_profile {
//    id bigint not null primary key auto_increment,
//    account_id bigint,
//    image varchar(255),
//    cccd varchar(15),
//    full_name nvarchar(255),
//    birthday datetime,
//    gender bit,
//    communes_id varchar(10),
//    telephone varchar(13),
//    exp nvarchar(255),
//    created_at datetime,
//    update_at datetime,
//    delete_at bit,
//    constraint FK_dentist_profile_communes foreign key(communes_id) references communes(id),
//    constraint FK_dentist_profile_accounts foreign key(account_id) references accounts(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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
}
