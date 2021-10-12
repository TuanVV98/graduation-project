package com.spring.dto.model;

import com.spring.model.VerificationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifycationTokenDTO {


    private Long id;

    @NotEmpty
    AccountsDTO accountsDTO;

    @NotBlank
    private String token;

    private String type;

    private Date createAt ;

    private LocalDateTime expiresAt ;

    private LocalDateTime cerified ;

    public VerificationToken convertDTOToEntity() {
        return new ModelMapper().map(this, VerificationToken.class);
    }
}
