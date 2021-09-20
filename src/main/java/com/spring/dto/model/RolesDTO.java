package com.spring.dto.model;

import com.spring.model.Roles;
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

public class RolesDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    public Roles convertDTOToEntity() {
        return new ModelMapper().map(this, Roles.class);
    }

}
