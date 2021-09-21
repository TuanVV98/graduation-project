package com.spring.dto.model;

import com.spring.model.VerifycationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifycationTokenDTO {

    @NotNull
    private Long id;

    @NotEmpty
    AccountsDTO accountsDTO;

    @NotBlank
    private String token;

    private String type;

    private Date createAt = new Date();

    private Date expiresAt = new Date();

    private Date cerified = new Date();

    public VerifycationToken convertDTOToEntity() {
        return new ModelMapper().map(this, VerifycationToken.class);
    }
}
