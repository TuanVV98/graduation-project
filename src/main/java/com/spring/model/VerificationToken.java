package com.spring.model;

import com.spring.dto.model.VerifycationTokenDTO;
import com.spring.enumeration.VerificationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verifycation_token")
public class VerificationToken implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "type")
    private VerificationEnum type;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @Column(name = "expires_at")
    private LocalDateTime expiresAt ;

    @Column(name = "cerified")
    private LocalDateTime cerified ;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private  Accounts accounts;

    public VerifycationTokenDTO convertEntityToDTO() {
        return new ModelMapper().map(this, VerifycationTokenDTO.class);
    }
}
