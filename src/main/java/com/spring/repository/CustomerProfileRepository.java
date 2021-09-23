package com.spring.repository;

import com.spring.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    @Query("Select c from CustomerProfile c where c.deleteAt = false")
    List<CustomerProfile> findByDeleteAtIsTrue();
    List<CustomerProfile> findByDeleteAtIsFalse();
}
