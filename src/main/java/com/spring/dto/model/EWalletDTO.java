package com.spring.dto.model;

import com.spring.model.EWallet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EWalletDTO {

//    @NotNull
    private Long id;

    private Double balance;

    @NotBlank
    CustomerProfileDTO customerProfileDTO;

    public EWallet convertDTOToEntity() {
        return new ModelMapper().map(this, EWallet.class);
    }

}
