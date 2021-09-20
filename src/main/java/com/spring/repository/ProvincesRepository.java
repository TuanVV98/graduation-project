package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvincesRepository extends JpaRepository<Provinces, String> {
}
