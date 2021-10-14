package com.spring.dto.model;

import com.spring.model.CustomerProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileDTO {

    
    private Long id;

    @NotBlank
    AccountsDTO accountsDTO;

    private String image;

    @NotBlank(message = "Không được để trống họ tên")
    @Size(max = 30)
    private String fullname;

    private Date birthday = new Date();

    @NotNull
    private Boolean gender;

    @NotBlank
    CommunesDTO communesDTO;

    @NotBlank(message = "Không được để trống số điện thoại")
    @Size(max = 30)
    private String telephone;

    @NotBlank(message = "Không được để trống story")
    private String story;

    private Date createAt = new Date();

    private Date updateAt = new Date();

    private Boolean deleteAt;

    public CustomerProfile convertDTOToEntity() {
        return new ModelMapper().map(this, CustomerProfile.class);
    }
}
