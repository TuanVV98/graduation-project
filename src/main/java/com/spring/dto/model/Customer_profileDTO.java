package com.spring.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer_profileDTO {

    private Integer id;

    AccountsDTO accounts;

    private String image;

    private String fullname;

    private Date birthday = new Date();

    private Boolean gender;

    CommunesDTO communes;

    private String telephone;

    private String story;

    private Date createAt = new Date();

    private Date updateAt = new Date();

    private Boolean deleteAt;
}
