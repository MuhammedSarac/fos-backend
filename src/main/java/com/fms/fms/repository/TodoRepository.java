package com.fms.fms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fms.fms.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByFamilyMemberId(Long familieMemberId);
	List<Todo> findByFamilyId(Long familyId);

}
