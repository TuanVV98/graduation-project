package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("select s from Service s where s.deleteAt = 0")
    List<Service> findAllCustom();

    @Query("select s from Service s where s.deleteAt = 0 and s.id = ?1")
    Service findByIdCustom(Long id);

    @Query("select s from Service s where s.deleteAt = 0 and s.name = ?1")
    Service findByNameCustom(String name);

    @Query("select count(s)>0 from Service s where s.deleteAt = 0 and s.id = ?1")
    boolean existByIdCustom(Long id);

    @Query("select count(s)>0 from Service s where s.deleteAt = 0 and s.name = ?1")
    boolean existByNameCustom(String username);
}
