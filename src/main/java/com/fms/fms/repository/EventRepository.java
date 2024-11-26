package com.fms.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fms.fms.entity.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findByFamilyId(Long familyId);
	List<Event> findByFamilyMemberId(Long familyMemberId);

}
