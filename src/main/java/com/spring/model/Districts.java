package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.DistrictsDTO;
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
@Table(name = "districts")
public class Districts implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "province_id")
    Provinces provinces;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "districts")
    List<Communes> communes;

    public DistrictsDTO convertEntityToDTO() {
        return new ModelMapper().map(this, DistrictsDTO.class);
    }
}
