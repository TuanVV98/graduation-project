package com.spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @ManyToOne
    @JoinColumn(name = "post_id")
    Posts posts;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accounts;

}
