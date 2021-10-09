package com.spring.repository;

import com.spring.model.Accounts;
import com.spring.model.CustomerProfile;
import com.spring.model.DentistProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DentistProfileRepository extends JpaRepository<DentistProfile, Long> {
    List<DentistProfile> findAllByDeleteAtIsFalse();
    List<DentistProfile> findAllByDeleteAtIsTrue();
    Optional<DentistProfile> findByAccounts(Accounts accounts);
}
