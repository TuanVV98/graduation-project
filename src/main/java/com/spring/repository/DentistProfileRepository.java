package com.spring.repository;

import com.spring.model.DentistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistProfileRepository extends JpaRepository<DentistProfile, Long> {
}
