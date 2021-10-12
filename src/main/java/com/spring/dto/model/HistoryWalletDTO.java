package com.spring.dto.model;

import com.spring.model.HistoryWallet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryWalletDTO {

    @NotNull
    private Long id;

    private Date createAt = new Date();

    @NotBlank
    EWalletDTO eWalletDTO;

    private String description;

    public HistoryWallet convertDTOToEntity() {
        return new ModelMapper().map(this, HistoryWallet.class);
    }

}
