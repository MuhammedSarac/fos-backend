package com.fms.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.fms.dto.FamilyMemberDto;
import com.fms.fms.service.FamilyMemberService;

@RestController
@RequestMapping("/familymembers")
public class FamilyMemberController {
	private final FamilyMemberService familyMemberService;

	@Autowired
	public FamilyMemberController(FamilyMemberService familyMemberService) {
	    this.familyMemberService = familyMemberService;
	}
	
	@PostMapping
	public ResponseEntity<FamilyMemberDto> createFamilyMember (@RequestBody FamilyMemberDto familyMemberDto){
		FamilyMemberDto savedFamilyMember = familyMemberService.createFamilyMember(familyMemberDto);
		return new ResponseEntity<>(savedFamilyMember, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<FamilyMemberDto> getFamilyMemberById(@PathVariable("id")Long familyMemberId){
		FamilyMemberDto familyMemberDto = familyMemberService.getFamilyMemberById(familyMemberId);
		return ResponseEntity.ok(familyMemberDto);
	
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<List<FamilyMemberDto>> getAllFamilyMembers(@PathVariable("id")Long familyId){
		List<FamilyMemberDto> familyMembersDtos = familyMemberService.getAllFamilyMember(familyId);
		return ResponseEntity.ok(familyMembersDtos);
		
	}
	@PutMapping("{id}")
	public ResponseEntity<FamilyMemberDto> updateFamilyMember(@PathVariable("id") Long familyMemberId, 
			@RequestBody FamilyMemberDto familyMemberDto){
		FamilyMemberDto familyMemberDtoUpdated = familyMemberService.updateFamilyMember(familyMemberId, familyMemberDto);
		return ResponseEntity.ok(familyMemberDtoUpdated);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<FamilyMemberDto> deleteFamilyMember(@PathVariable("id") Long familyMemberId){
		familyMemberService.deleteFamilyMember(familyMemberId);
		return ResponseEntity.noContent().build();
	}
}
