package com.spring.dto.model;

import com.spring.model.Accounts;
import com.spring.utils.BcryptUtil;
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

    private String password;

    @NotBlank(message = "Không được để trống sđt")
    @Size(max = 10)
    private String telephone;

    private Date updateAt ;

    @NotBlank
    String rolesId;

    private Boolean deleteAt;

    public AccountsDTO(Long id, String rolesId) {
        this.id = id;
        this.rolesId = rolesId;
    }

    public String getPassword() {
        return BcryptUtil.getHash(this.password);
    }

    public Accounts convertDTOToEntity() {
        return new ModelMapper().map(this, Accounts.class);
    }
}