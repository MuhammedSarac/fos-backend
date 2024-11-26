package com.fms.fms.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.fms.dto.TodoDto;
import com.fms.fms.entity.Family;
import com.fms.fms.entity.FamilyMember;
import com.fms.fms.entity.Todo;
import com.fms.fms.exception.ResourceNotFoundException;
import com.fms.fms.mapper.TodoMapper;
import com.fms.fms.repository.FamilyMembersRepository;
import com.fms.fms.repository.FamilyRepository;
import com.fms.fms.repository.TodoRepository;
import com.fms.fms.service.TodoService;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements TodoService{
	private final TodoRepository todoRepository;
	private final FamilyMembersRepository familyMemberRepository;
	private final FamilyRepository familyRepository;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository, FamilyRepository familyRepository, FamilyMembersRepository familyMemberRepository) {
		this.todoRepository = todoRepository;
		this.familyRepository = familyRepository;
		this.familyMemberRepository = familyMemberRepository;
	}

	@Override
	@Transactional
	public TodoDto craateTodo(TodoDto todoDto) {
		FamilyMember familyMember = familyMemberRepository.findById(todoDto.getFamilyMemberId()).orElseThrow();
	    Family family = familyRepository.findById(todoDto.getFamilyId()).orElseThrow();
		Todo todo = TodoMapper.mapToTodo(todoDto,familyMember, family);
		todo = todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(todo);
	}

	@Override
	@Transactional
	public TodoDto updateTodo(long id, TodoDto todoDto) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(()-> 
				new ResourceNotFoundException("Todo not found with given id : " + id));
		todo.setDescription(todoDto.getDescription());
		todo.setHours(todoDto.getHours());
		todo.setPlanedStartDateTime(todoDto.getPlanedStartDateTime());
		todo.setPlanedEndDateTime(todoDto.getPlanedEndDateTime());
		todo.setStatus(todoDto.getStatus());
		
		todo = todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(todo);
	}

	@Override
	public TodoDto geTodoById(long TodoId) {
		Todo todo  = todoRepository.findById(TodoId)
				.orElseThrow();
		return TodoMapper.mapToTodoDto(todo);
	}

	@Override
	public List<TodoDto> getAllTodoByFamilymemberId(long familyMemberId) {
		List<Todo> todos = todoRepository.findByFamilyMemberId(familyMemberId);
		return todos.stream().map(TodoMapper::mapToTodoDto).collect(Collectors.toList());
	}

	@Override
	public List<TodoDto> getAllTodoByFamilyId(long familyId) {
		List<Todo> todos = todoRepository.findByFamilyId(familyId);
		return todos.stream().map(TodoMapper::mapToTodoDto).collect(Collectors.toList());
	}

	@Override
	public void deleteTodo(long id) {
		todoRepository.findById(id)
		.orElseThrow(()-> 
		new ResourceNotFoundException("Todo not found with given id : " + id));
		todoRepository.deleteById(id);
		
	}

}
