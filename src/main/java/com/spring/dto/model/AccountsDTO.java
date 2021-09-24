package com.spring.dto.model;

import com.spring.model.Accounts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDTO {

    private Long id;

    @NotEmpty(message = "Không được để trống email")
    @Email(message = "Sai định dạng")
    private String email;

    @NotBlank(message = "Không được để trống sđt")
    @Size(max = 10)
    private String telephone;

    private Date updateAt = new Date();

    @NotBlank
    RolesDTO roles;

    private Boolean deleteAt;

    public Accounts convertDTOToEntity() {
        return new ModelMapper().map(this, Accounts.class);
    }
}
