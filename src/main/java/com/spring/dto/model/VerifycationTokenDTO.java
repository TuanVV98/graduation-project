package com.spring.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifycationTokenDTO {

    private Integer id;

    AccountsDTO accountsDTO;

    private String token;

    private String type;

    private Date createAt = new Date();

    private Date expiresAt = new Date();

    private Date cerified = new Date();
}
