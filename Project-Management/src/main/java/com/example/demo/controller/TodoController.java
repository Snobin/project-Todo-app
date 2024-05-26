package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TodoDto;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<ResponseDto> addTodo(@RequestBody @Valid TodoDto todoDto) {
        return todoService.addTodo(todoDto);
    }

    @PutMapping("/{pid}/todos/{tid}")
    public ResponseEntity<ResponseDto> updateTodo(@PathVariable Integer pid, @PathVariable Integer tid, @RequestBody @Valid TodoDto todoDto) {
        return todoService.updateTodo(pid, tid, todoDto);
    }


    @DeleteMapping("/{projectId}/todos/{todoId}")
    public ResponseEntity<ResponseDto> deleteTodo(@PathVariable Integer projectId, @PathVariable Integer todoId) {
        return todoService.deleteTodo(projectId, todoId);
    }

    @PutMapping("/{projectId}/complete/{id}")
    public ResponseEntity<ResponseDto> markTodoAsComplete(@PathVariable Integer projectId,@PathVariable Integer id) {
        return todoService.markTodoAsComplete(projectId,id);
    }
    
    @PutMapping("/{projectId}/pending/{id}")
    public ResponseEntity<ResponseDto> markTodoAsPending(@PathVariable Integer projectId,@PathVariable Integer id) {
        return todoService.markTodoAsPending(projectId,id);
    }
    @GetMapping("/{projectId}/todos/{todoId}")
    public ResponseEntity<ResponseDto> getTodoById(@PathVariable Integer projectId, @PathVariable Integer todoId) {
        return todoService.getTodoById(projectId, todoId);
    }

}