package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.PostsDTO;
import com.spring.dto.model.ProvincesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provinces")
public class Provinces {
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
