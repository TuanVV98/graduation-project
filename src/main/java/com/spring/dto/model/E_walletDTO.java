package com.spring.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class E_walletDTO {

    private Integer id;

    private Double balance;

    Customer_profileDTO customer_profile;
}
