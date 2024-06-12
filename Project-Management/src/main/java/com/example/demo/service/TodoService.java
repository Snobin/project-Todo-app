package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TodoDto;
import org.springframework.http.ResponseEntity;

public interface TodoService {

	ResponseEntity<ResponseDto> addTodo(TodoDto todoDto);

	ResponseEntity<ResponseDto> updateTodo(TodoDto updatedTodoDto);

	ResponseEntity<ResponseDto> deleteTodo(Integer pid, Integer tid);

	ResponseEntity<ResponseDto> markTodoAsComplete(Integer pid, Integer id);

	public ResponseEntity<ResponseDto> updateTodo(Integer pid, Integer tid, TodoDto updatedTodoDto);

	public ResponseEntity<ResponseDto> markTodoAsPending(Integer pid, Integer id);

	public ResponseEntity<ResponseDto> getTodoById(Integer pid, Integer tid);
}
