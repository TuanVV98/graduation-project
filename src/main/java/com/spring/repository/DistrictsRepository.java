package com.spring.repository;

import com.spring.model.DentistProfile;
import com.spring.model.Districts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictsRepository extends JpaRepository<Districts, String> {
}
