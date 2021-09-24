package com.spring.model;

import com.spring.dto.model.HistoryWalletDTO;
import com.spring.dto.model.LikesDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "account_id"})
})
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt = new Date();

    @ManyToOne
    @JoinColumn(name = "post_id")
    Posts posts;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accounts;

    public LikesDTO convertEntityToDTO() {
        return new ModelMapper().map(this, LikesDTO.class);
    }

}
