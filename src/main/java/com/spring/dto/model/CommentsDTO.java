package com.spring.dto.model;

import com.spring.model.Comments;
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
public class CommentsDTO {

//    @NotNull
    private Long id;

    @NotBlank(message = "Không được để trống content")
    private String content;

    private String image;

    private Date createAt = new Date();

    @NotNull(message = "Không được để trống postsId")
    private Long postsId;

    @NotNull(message = "Không được để trống accountsId")
    private Long accountsId;

    private Boolean deleteAt;

    public Comments convertDTOToEntity() {
        return new ModelMapper().map(this, Comments.class);
    }

}
