package com.spring.repository;

import com.spring.model.BookingDetail;
import com.spring.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    //read all by deleteAt=TRUE
    public List<Comments> findByDeleteAtIsTrue();

    //read all by deleteAT=FALSE
    public List<Comments> findByDeleteAtIsFalse();

    //read all comment by post_id
    @Query("Select e From Comments e WHERE e.posts.id=:postsId AND e.deleteAt=FALSE")
    public List<Comments> findAllCommentsByPostId(@Param("postsId") Long postsId);

    //read all comment by post_id and account_id
    @Query("Select e From Comments e WHERE e.posts.id=:postsId AND e.accounts.id=:accountsId AND e.deleteAt=FALSE ")
    public List<Comments> findAllCommentsByPostIdAndAccountId
    (@Param("postsId") Long postsId, @Param("accountsId") Long accountsId);


}
