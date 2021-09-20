package com.spring.dto.model;

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
public class PostsDTO {

    private Integer id;

    private String content;

    private String image;

    AccountsDTO accountsDTO;

    private Boolean deleteAt;
}
