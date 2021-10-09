package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.dto.model.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Posts implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accounts;

    @Column(name = "delete_at")
    private Boolean deleteAt;

//one to many
    @JsonIgnore
    @OneToMany(mappedBy = "posts")
    List<Comments> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "posts")
    List<Likes> likes;

    public PostDTO convertEntityToDTO() {
        return new ModelMapper().map(this, PostDTO.class);
    }
}
