package com.spring.service.likes;

import com.spring.dto.model.LikesDTO;

import java.util.List;

public interface LikesService {
    List<LikesDTO> getLikeByPost(Long id);

    LikesDTO create(LikesDTO likesDTO);

    LikesDTO delete(Long id);

    LikesDTO getLikeByAccAndPost(Long ida, Long idp);
}
