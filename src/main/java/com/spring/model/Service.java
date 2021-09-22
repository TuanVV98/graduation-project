package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.ServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    @CreatedDate
    private Date createAt;

    @Column(name = "delete_at")
    private Boolean deleteAt;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "service")
    List<BookingDetail> bookingDetails;
    
    public ServiceDTO convertEntityToDTO() {
        return new ModelMapper().map(this, ServiceDTO.class);
    }

    
    
	
}
