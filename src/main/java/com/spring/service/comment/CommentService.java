package com.spring.service.comment;

import com.spring.dto.model.CommentsDTO;

import java.util.List;

public interface CommentService {


    public List<CommentsDTO> readAll();

    public CommentsDTO create(CommentsDTO dto);

    public CommentsDTO update(CommentsDTO dto);

    public CommentsDTO delete(Long id);

    //soft-delete
    public CommentsDTO updateDeleteAt(Long id,Boolean deleteAt);

    public List<CommentsDTO> readAllDeleteAtTrue();

    public List<CommentsDTO> readAllDeleteAtFalse();

    //hiển thị comment theo Id bài viết
    public List<CommentsDTO> readAllCommentByPostId(Long postsId);

    //hiển thị comment theo Id bài viết và theo Id người dùng
    public List<CommentsDTO> readAllCommentByPostIdAndAccountId(Long postsId,Long accountsId);
}
