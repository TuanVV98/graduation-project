package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {


    @Query( value = "SELECT p FROM Posts p WHERE p.title =:title")
    List<Posts> findAllByTitle(@Param("title") String title);
}
