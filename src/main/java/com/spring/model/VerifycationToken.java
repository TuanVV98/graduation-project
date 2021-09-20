package com.spring.model;

import com.spring.dto.model.ServiceDTO;
import com.spring.dto.model.VerifycationTokenDTO;
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
@Table(name = "verifycation_token")
public class VerifycationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    public VerifycationTokenDTO convertEntityToDTO() {
        return new ModelMapper().map(this, VerifycationTokenDTO.class);
    }
}
