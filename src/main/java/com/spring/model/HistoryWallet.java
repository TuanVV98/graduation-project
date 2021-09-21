package com.spring.model;

import com.spring.dto.model.EWalletDTO;
import com.spring.dto.model.HistoryWalletDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history_wallet")
public class HistoryWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    EWallet eWallet;

    @Column(name = "description")
    private String description;

    public HistoryWalletDTO convertEntityToDTO() {
        return new ModelMapper().map(this, HistoryWalletDTO.class);
    }

}
