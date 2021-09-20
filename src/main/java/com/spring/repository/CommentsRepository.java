package com.spring.repository;

import com.spring.model.BookingDetail;
import com.spring.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
