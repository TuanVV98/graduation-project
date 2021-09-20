package com.spring.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.Posts;
import com.spring.model.Provinces;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvincesDTO {

    @NotBlank(message = "Không được để trống id")
    private String id;

    @NotBlank(message = "Không được để trống tên tỉnh")
    private String name;

    @NotBlank(message = "Không được để trống")
    private String type;

    public Provinces convertDTOToEntity() {
        return new ModelMapper().map(this, Provinces.class);
    }

}
