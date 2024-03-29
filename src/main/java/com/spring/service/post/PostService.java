package com.spring.service.post;

import com.spring.dto.model.PostDTO;

import java.util.List;
import java.util.Optional;


public interface PostService {

    PostDTO save(PostDTO dto);

    PostDTO update(PostDTO dto);

    List<PostDTO> findByTitle(String title);

    Optional<PostDTO> findById(Long id);

    List<PostDTO> findAll();
}
