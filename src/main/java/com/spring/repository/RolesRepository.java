package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, String> {

}
