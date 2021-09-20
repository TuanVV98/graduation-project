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
public class AccountsDTO {

    private Integer id;

    private String password;

    private String email;

    private String telephone;

    private Date updateAt = new Date();

    RolesDTO rolesDTO;

    private Boolean deleteAt;
}
