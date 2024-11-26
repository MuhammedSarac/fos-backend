package com.fms.fms.service;

import java.util.List;

import com.fms.fms.dto.TodoDto;

public interface TodoService {
	TodoDto craateTodo(TodoDto todoDto);
	TodoDto updateTodo(long id, TodoDto todoDto);
	TodoDto geTodoById(long TodoId);
	List<TodoDto> getAllTodoByFamilymemberId(long familyMemberId);
	List<TodoDto> getAllTodoByFamilyId(long familyId);
	void deleteTodo(long id);

}
