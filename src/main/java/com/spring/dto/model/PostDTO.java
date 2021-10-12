package com.spring.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.Likes;
import com.spring.model.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {


    private Long id;

    @NotNull(message = "Title không để trống")
    private String title;

    @NotNull(message = "Content không để trống")
    private String content;

    private String image;

    @NotBlank
    private Long accountsId;

    private Boolean deleteAt;

    public Posts convertDTOToEntity() {
        return new ModelMapper().map(this, Posts.class);
    }

}
