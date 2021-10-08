package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("SELECT l FROM Likes l where l.posts.id=?1")
    List<Likes> findLikesByPostsId(Long id);

    @Query("SELECT l FROM Likes l where l.accounts.id=:ida and l.posts.id=:idp")
    Optional<Likes> findLikesByAccIdAndPostId(Long ida, Long idp);
}
