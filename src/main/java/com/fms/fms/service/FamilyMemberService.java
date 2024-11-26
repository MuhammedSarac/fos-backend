package com.fms.fms.service;

import java.util.List;

import com.fms.fms.dto.FamilyMemberDto;

public interface FamilyMemberService {
	FamilyMemberDto createFamilyMember (FamilyMemberDto familyMemberDto);
	FamilyMemberDto getFamilyMemberById (Long familyMemberId);
	List<FamilyMemberDto>getAllFamilyMember (Long familyId);
	FamilyMemberDto updateFamilyMember (Long familyMemberId, FamilyMemberDto familyMemberDto);
	void deleteFamilyMember (Long familyMemberId) ;

}
