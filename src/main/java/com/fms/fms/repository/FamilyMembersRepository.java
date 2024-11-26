package com.fms.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fms.fms.entity.FamilyMember;

public interface FamilyMembersRepository extends JpaRepository<FamilyMember, Long> {
	List<FamilyMember> findByFamilyId(Long familyId);
	

}
