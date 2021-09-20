package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
