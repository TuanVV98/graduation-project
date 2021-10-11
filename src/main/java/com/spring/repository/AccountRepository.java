package com.spring.repository;

import com.spring.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {


    @Query( value = "SELECT a FROM Accounts a WHERE a.email = :email AND a.deleteAt = false ")
    Optional<Accounts> checkIfEmailExistsAndDeletedAt(@Param("email") String email);

    Optional<Accounts> findByEmail(String email);

    Optional<Accounts> findByTelephone(String telephone);
}
