package com.insursa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insursa.persistence.model.Identity;

@Repository
public interface IdentityRepository extends JpaRepository<Identity, String> {
	
}