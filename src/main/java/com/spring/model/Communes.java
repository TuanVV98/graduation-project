package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "communes")
public class Communes {
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
}
