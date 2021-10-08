package com.spring.service.likes;

import com.spring.dto.model.LikesDTO;
import com.spring.model.Likes;
import com.spring.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService{

    @Autowired
    private LikesRepository likesRepository;

    @Override
    public List<LikesDTO> getLikeByPost(Long id) {
        List<LikesDTO> itemsDTO = new ArrayList<>();
        List<Likes> entityList = likesRepository.findLikesByPostsId(id);
        for (Likes entity : entityList){
            LikesDTO dto = entity.convertEntityToDTO();
            itemsDTO.add(dto);
        }
        return itemsDTO;
    }

    @Override
    public LikesDTO create(LikesDTO likesDTO) {
        Likes entity = likesDTO.convertDTOToEntity();
        entity.setCreateAt(new Date());
        likesRepository.save(entity);
        likesDTO.setId(entity.getId());
        return likesDTO;
    }

    @Override
    public LikesDTO delete(Long id) {
        Optional<Likes> optional = likesRepository.findById(id);
        if(optional.isPresent()){
            Likes entity = optional.get();
            LikesDTO dto = entity.convertEntityToDTO();
            likesRepository.delete(entity);
            return dto;
        }
        return null;
    }

    @Override
    public LikesDTO getLikeByAccAndPost(Long ida, Long idp) {
        Optional<Likes> optional = likesRepository.findLikesByAccIdAndPostId(ida, idp);
        if(optional.isPresent()){
            Likes entity = optional.get();
            LikesDTO dto = entity.convertEntityToDTO();
            return dto;
        }
        return null;
    }
}
