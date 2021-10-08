package com.spring.service.comment;

import com.spring.dto.model.CommentsDTO;
import com.spring.model.Comments;
import com.spring.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentsRepository commentsRepo;

    @Autowired
    public CommentServiceImpl(CommentsRepository commentsRepo) {
        this.commentsRepo = commentsRepo;
    }

    @Override
    public List<CommentsDTO> readAll() {
        List<CommentsDTO> itemDTO = new ArrayList<>();
        this.commentsRepo.findAll().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }


    @Override
    public CommentsDTO create(CommentsDTO dto) {
        Comments entity = dto.convertDTOToEntity();
        entity.setDeleteAt(false);
        this.commentsRepo.save(entity);
        return entity.convertEntityToDTO();
    }

    @Override
    public CommentsDTO update(CommentsDTO dto) {
        Optional<Comments> optional = this.commentsRepo.findById(dto.getId());
        if (optional.isPresent()) {
            Comments entity = dto.convertDTOToEntity();
            entity.setDeleteAt(false);
            this.commentsRepo.save(entity);
            return entity.convertEntityToDTO();
        }
        return null;
    }

    @Override
    public CommentsDTO delete(Long id) {
        Optional<Comments> optional = this.commentsRepo.findById(id);
        if (optional.isPresent()) {
            Comments entity = optional.get();
            CommentsDTO dto = entity.convertEntityToDTO();
            this.commentsRepo.delete(entity);
            return dto;
        }
        return null;
    }

    //soft-delete
    @Override
    public CommentsDTO updateDeleteAt(Long id, Boolean deleteAt) {
        Optional<Comments> optional = this.commentsRepo.findById(id);
        if (optional.isPresent()) {
            Comments entity = optional.get();
            entity.setDeleteAt(deleteAt);
            this.commentsRepo.save(entity);
            CommentsDTO dto = entity.convertEntityToDTO();
            return dto;
        }
        return null;
    }

    @Override
    public List<CommentsDTO> readAllDeleteAtTrue() {
        List<CommentsDTO> itemDTO = new ArrayList<>();
        this.commentsRepo.findByDeleteAtIsTrue().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    @Override
    public List<CommentsDTO> readAllDeleteAtFalse() {
        List<CommentsDTO> itemDTO = new ArrayList<>();
        this.commentsRepo.findByDeleteAtIsFalse().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    //read all comment by post_id
    @Override
    public List<CommentsDTO> readAllCommentByPostId(Long postsId) {
        List<CommentsDTO> itemDTO = new ArrayList<>();
        this.commentsRepo.findAllCommentsByPostId(postsId).forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    // read all comment by post_id and account_id
    @Override
    public List<CommentsDTO> readAllCommentByPostIdAndAccountId(Long postsId, Long accountsId) {
        List<CommentsDTO> itemDTO = new ArrayList<>();
        this.commentsRepo.findAllCommentsByPostIdAndAccountId(postsId, accountsId).forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }
}
