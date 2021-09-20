package com.spring.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    private String id;

    private String content;

    private String image;

    private Double sale;

    private Date start = new Date();

    private Date end = new Date();

    private Date createAt = new Date();

    private Boolean deleteAt;

}
