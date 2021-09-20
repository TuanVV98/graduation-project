package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "e_wallet")
public class EWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerProfile customerProfile;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "eWallet")
    List<HistoryWallet> historyWallets;
}
