package com.spring.dto.model;

import com.spring.model.Voucher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    @NotBlank(message = "Không được để trống")
    private String id;

    @NotBlank(message = "Không được để trống nội dung")
    private String content;

    private String image;

    @NotNull
    @DecimalMin(value = "0")
    private Double sale;

    private Date start = new Date();

    private Date end = new Date();

    private Date createAt = new Date();

    private Boolean deleteAt;

    public Voucher convertDTOToEntity() {
        return new ModelMapper().map(this, Voucher.class);
    }

}
