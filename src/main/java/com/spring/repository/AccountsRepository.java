package com.spring.repository;

import com.spring.model.Accounts;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    @Query("select count(a) > 0 from Accounts a where a.deleteAt = false and a.id = ?1")
    boolean checkIdExist(Long id);

    @Query("select count(a) > 0 from Accounts a where a.deleteAt = false and a.email = ?1")
    boolean checkEmailExist(String email);

    Optional<Accounts> findOneByIdIgnoreCase(Long id);

    Optional<Accounts> findOneByEmailIgnoreCase(String email);

}
