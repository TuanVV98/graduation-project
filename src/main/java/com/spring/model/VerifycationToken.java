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
@Table(name = "verifycation_token")
public class VerifycationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accounts;

    @Column(name = "token")
    private String token;

    @Column(name = "type")
    private String type;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "expires_at")
    private Date expiresAt = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "cerified")
    private Date cerified = new Date();
}
