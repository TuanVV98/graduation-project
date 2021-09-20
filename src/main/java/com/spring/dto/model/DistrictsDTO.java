package com.spring.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictsDTO {

    private String id;

    private String name;

    private String type;

    ProvincesDTO provincesDTO;
}
