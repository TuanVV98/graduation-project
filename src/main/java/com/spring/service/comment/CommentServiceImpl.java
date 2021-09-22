package com.spring.service.comment;

import com.spring.dto.model.CommentsDTO;
import com.spring.model.Comments;
import com.spring.repository.CommentsRepository;
import com.spring.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentsRepository commentsRepo;
    private MapperUtil mapperUtil;

    @Autowired
    public CommentServiceImpl(
            CommentsRepository commentsRepo,
            MapperUtil mapperUtil
    ){
        this.commentsRepo=commentsRepo;
        this.mapperUtil=mapperUtil;
    }

    @Override
    public List<CommentsDTO> readAll() {
        return this.mapperUtil.mapList(this.commentsRepo.findAll(),CommentsDTO.class);
    }


    @Override
    public CommentsDTO create(CommentsDTO dto) {
         Comments entity=dto.convertDTOToEntity();
         entity.setDeleteAt(false);
         this.commentsRepo.save(entity);
         return entity.convertEntityToDTO();
    }

    @Override
    public CommentsDTO update(CommentsDTO dto) {
        Optional<Comments> optional=this.commentsRepo.findById(dto.getId());
        if(optional.isPresent()){
            Comments entity=dto.convertDTOToEntity();
            this.commentsRepo.save(entity);
        }
        return dto;
    }

    @Override
    public CommentsDTO delete(Long id) {
        Optional<Comments> optional=this.commentsRepo.findById(id);
        if(optional.isPresent()){
            Comments entity= optional.get();
            CommentsDTO dto=entity.convertEntityToDTO();
            this.commentsRepo.delete(entity);
            return dto;
        }
        return null;
    }

    //soft-delete
    @Override
    public CommentsDTO updateDeleteAt(Long id, Boolean deleteAt) {
        Optional<Comments> optional=this.commentsRepo.findById(id);
        if(optional.isPresent()){
            Comments entity= optional.get();
            entity.setDeleteAt(deleteAt);
            this.commentsRepo.save(entity);
            CommentsDTO dto=entity.convertEntityToDTO();
            return dto;
        }
        return null;
    }

    @Override
    public List<CommentsDTO> readAllDeleteAtTrue() {
        return this.mapperUtil.mapList(this.commentsRepo.findByDeleteAtIsTrue(),CommentsDTO.class);
    }

    @Override
    public List<CommentsDTO> readAllDeleteAtFalse() {
        return this.mapperUtil.mapList(this.commentsRepo.findByDeleteAtIsFalse(),CommentsDTO.class);
    }

    //read all comment by post_id
    @Override
    public List<CommentsDTO> readAllCommentByPostId(Long postsId) {
        return this.mapperUtil.mapList(this.commentsRepo.findAllCommentsByPostId(postsId),CommentsDTO.class);
    }

    // read all comment by post_id and account_id
    @Override
    public List<CommentsDTO> readAllCommentByPostIdAndAccountId(Long postsId, Long accountsId) {
        return this.mapperUtil.mapList
                (this.commentsRepo.findAllCommentsByPostIdAndAccountId(postsId, accountsId),CommentsDTO.class);
    }
}
