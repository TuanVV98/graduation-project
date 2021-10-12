package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.RolesDTO;
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
@Table(name = "roles")
public class Roles {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    //one to many
    @JsonIgnore
    @OneToMany(mappedBy = "roles")
    List<Accounts> accounts;

    public RolesDTO convertEntityToDTO() {
        return new ModelMapper().map(this, RolesDTO.class);
    }
}
