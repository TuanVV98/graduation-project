package com.spring.dto.model;

import com.spring.model.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsDTO {

    @NotNull
    private Long id;

    private String content;

    private String image;

    @NotBlank
    AccountsDTO accountsDTO;

    private Boolean deleteAt;

    public Posts convertDTOToEntity() {
        return new ModelMapper().map(this, Posts.class);
    }

}
