package com.fms.fms.mapper;

import com.fms.fms.dto.FamilyMemberDto;
import com.fms.fms.entity.FamilyMember;

public class FamilyMemberMapper {
	
	
	public static FamilyMemberDto mapToFamilyMemberDto (FamilyMember familyMember) {
		return new FamilyMemberDto(
				familyMember.getId(),
				familyMember.getName(),
				familyMember.getBirthday(),
				familyMember.getFamilyId()
		);
		
	} 
	public static FamilyMember mapToFamilyMember (FamilyMemberDto familyMemberDto) {
		
		return new FamilyMember(
				familyMemberDto.getId(),
				familyMemberDto.getName(),
				familyMemberDto.getBirthday(),
				familyMemberDto.getFamilyId()
		);
	}

}
