package com.fms.fms.dto;

import java.time.LocalDate;

public class FamilyMemberDto {
	
	public FamilyMemberDto(){
		
	}
	


	public FamilyMemberDto(Long id, String name, LocalDate birthday, Long familyId) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.familyId = familyId;
	}
	private Long id;
	private String name;
	private LocalDate birthday;
	private Long familyId;
	
	public Long getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	

}
