package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.CommunesDTO;
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
@Table(name = "communes")
public class Communes implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "district_id")
    Districts districts;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "communes")
    List<CustomerProfile> customerProfiles;

    @JsonIgnore
    @OneToMany(mappedBy = "communes")
    List<DentistProfile> dentistProfiles;

    public CommunesDTO convertEntityToDTO() {
        return new ModelMapper().map(this, CommunesDTO.class);
    }
}
