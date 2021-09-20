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
public class HistoryWalletDTO {

    private Integer id;

    private Date createAt = new Date();

    EWalletDTO eWalletDTO;

    private String description;

}
