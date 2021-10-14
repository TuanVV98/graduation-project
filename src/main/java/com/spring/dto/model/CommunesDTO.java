package com.spring.dto.model;

import com.spring.model.Communes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunesDTO {

//    @NotBlank(message = "Không được để trống")
    private String id;

    @NotBlank(message = "Không được để trống")
    private String name;

    @NotBlank(message = "Không được để trống")
    private String type;

    @NotBlank
    DistrictsDTO districtsDTO;

    public Communes convertDTOToEntity() {
        return new ModelMapper().map(this, Communes.class);
    }

}
