package com.spring.repository;

import com.spring.model.Comments;
import com.spring.model.Communes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunesRepository extends JpaRepository<Communes, String> {
}
