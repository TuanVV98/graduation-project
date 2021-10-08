package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.ProvincesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provinces")
public class Provinces implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "provinces")
    List<Districts> districts;

    public ProvincesDTO convertEntityToDTO() {
        return new ModelMapper().map(this, ProvincesDTO.class);
    }
}
