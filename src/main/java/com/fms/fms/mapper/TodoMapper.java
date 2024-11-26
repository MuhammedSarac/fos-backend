package com.fms.fms.mapper;


import com.fms.fms.dto.TodoDto;
import com.fms.fms.entity.Todo;
import com.fms.fms.entity.Family;
import com.fms.fms.entity.FamilyMember;

public class TodoMapper {
	
	public static TodoDto mapToTodoDto (Todo todo) {
		TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setDescription(todo.getDescription());
        dto.setStatus(todo.getStatus());
        dto.setHours(todo.getHours());
        dto.setPlanedStartDateTime(todo.getPlanedStartDateTime());
        dto.setPlanedEndDateTime(todo.getPlanedEndDateTime());
        dto.setFamilyMemberId(todo.getFamilyMember() != null ? todo.getFamilyMember().getId() : null);
        dto.setFamilyId(todo.getFamily() != null ? todo.getFamily().getId() : null);
        return dto;
		
		
	}
	
	public static Todo mapToTodo(TodoDto dto, FamilyMember familyMember, Family family ) {
        Todo todo = new Todo();
        todo.setId(dto.getId());
        todo.setDescription(dto.getDescription());
        todo.setStatus(dto.getStatus());
        todo.setHours(dto.getHours());
        todo.setPlanedStartDateTime(dto.getPlanedStartDateTime());
        todo.setPlanedEndDateTime(dto.getPlanedEndDateTime());
        todo.setFamilyMember(familyMember);
        todo.setFamily(family);
        return todo;
	}

}
