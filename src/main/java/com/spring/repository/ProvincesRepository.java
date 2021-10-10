package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincesRepository extends JpaRepository<Provinces, String> {
}
