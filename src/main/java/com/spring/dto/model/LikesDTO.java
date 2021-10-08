package com.spring.dto.model;

import com.spring.model.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikesDTO {

    @NotNull
    private Long id;

    private Date createAt = new Date();

    @NotBlank
    PostDTO postsDTO;

    @NotBlank
    AccountsDTO accountsDTO;

    public Likes convertDTOToEntity() {
        return new ModelMapper().map(this, Likes.class);
    }

}
