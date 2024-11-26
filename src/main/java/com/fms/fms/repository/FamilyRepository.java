package com.fms.fms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fms.fms.entity.Family;

public interface FamilyRepository  extends JpaRepository<Family, Long>{
	Optional<Family> findByEmail(String email);

}
