package com.fms.fms.service.implement;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.fms.dto.FamilyMemberDto;
import com.fms.fms.entity.FamilyMember;
import com.fms.fms.exception.ResourceNotFoundException;
import com.fms.fms.mapper.FamilyMemberMapper;
import com.fms.fms.repository.FamilyMembersRepository;
import com.fms.fms.service.FamilyMemberService;

import jakarta.transaction.Transactional;


@Service
public class FamilyMemberServiceImpl implements FamilyMemberService{
	private final FamilyMembersRepository  familyMembersRepository;
	
	@Autowired
	public FamilyMemberServiceImpl (FamilyMembersRepository familyMembersRepository) {
		this.familyMembersRepository = familyMembersRepository;
	}

	@Override
	@Transactional
	public FamilyMemberDto createFamilyMember(FamilyMemberDto familyMemberDto) {
		FamilyMember familyMember = FamilyMemberMapper.mapToFamilyMember(familyMemberDto);
		FamilyMember savedFamilyMember = familyMembersRepository.save(familyMember);
		
		return FamilyMemberMapper.mapToFamilyMemberDto(savedFamilyMember);
	}

	@Override
	public FamilyMemberDto getFamilyMemberById(Long familyMemberId) {
		FamilyMember familyMember = familyMembersRepository.findById(familyMemberId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Familymember is not found with given id : " + familyMemberId));
		return FamilyMemberMapper.mapToFamilyMemberDto(familyMember);
		
	}



	@Override
	@Transactional
	public FamilyMemberDto updateFamilyMember(Long familyMemberId, FamilyMemberDto familyMemberDto) {
		FamilyMember familyMember = familyMembersRepository.findById(familyMemberId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Familymember is not found with given id : " + familyMemberId));
		familyMember.setName(familyMemberDto.getName());
		familyMember.setBirthday(familyMemberDto.getBirthday());
		FamilyMember updatedFamilyMember = familyMembersRepository.save(familyMember);
		return FamilyMemberMapper.mapToFamilyMemberDto(updatedFamilyMember);
	}

	@Override
	@Transactional
	public void deleteFamilyMember(Long familyMemberId) {
		familyMembersRepository.findById(familyMemberId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Familymember is not found with given id : " + familyMemberId));
		familyMembersRepository.deleteById(familyMemberId);

		
	}

	@Override
	public List<FamilyMemberDto> getAllFamilyMember(Long familyId) {
		List<FamilyMember> familyMembers = familyMembersRepository.findByFamilyId(familyId);
		
		return familyMembers.stream()
				.map((familyMember) -> FamilyMemberMapper.mapToFamilyMemberDto(familyMember))
				.collect(Collectors.toList());
	}

}
