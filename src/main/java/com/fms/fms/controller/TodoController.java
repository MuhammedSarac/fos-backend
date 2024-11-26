package com.fms.fms.controller;

import java.util.List;

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

import com.fms.fms.dto.TodoDto;
import com.fms.fms.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private final TodoService todoService;
	
	private TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	@PostMapping
	public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){
		TodoDto savedTodoDto= todoService.craateTodo(todoDto);
		return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto){
		TodoDto savedTodoDto = todoService.updateTodo(todoId, todoDto);
		return ResponseEntity.ok(savedTodoDto);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getByIdTodo(@PathVariable("id") Long todoId){
		TodoDto todoDto = todoService.geTodoById(todoId);
		return ResponseEntity.ok(todoDto);
	}
	@GetMapping("/getbyfamilymemberid/{id}")
	public ResponseEntity<List<TodoDto>> getByFamilyMemberIdTodos(@PathVariable("id") Long familyMemberId){
		List<TodoDto> todos= todoService.getAllTodoByFamilymemberId(familyMemberId);
		return ResponseEntity.ok(todos);
		
	}
	@GetMapping("/getbyfamilyid/{id}")
	public ResponseEntity<List<TodoDto>> getByFamilyIdTodos(@PathVariable("id") Long familyId){
		List<TodoDto> todos = todoService.getAllTodoByFamilyId(familyId);
		return ResponseEntity.ok(todos);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Long> deleteTodo(@PathVariable("id") Long todoId){
		todoService.deleteTodo(todoId);
		return ResponseEntity.ok(todoId);
	}
	

}
