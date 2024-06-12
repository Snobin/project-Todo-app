package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TodoDto;
import com.example.demo.entity.Project;
import com.example.demo.entity.Todo;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TodoRepository;
import com.example.demo.util.Constants;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResponseEntity<ResponseDto> addTodo(TodoDto todoDto) {
        Optional<Project> optionalProject = projectRepository.findById(todoDto.getProjectId());
        
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            Todo todo = new Todo();
            todo.setDescription(todoDto.getDescription());
            todo.setCreatedDate(LocalDateTime.now());
            todo.setStatus("pending");
            todo.setProject(project);
            todoRepository.save(todo);

            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo added successfully.");
            responseDto.setStatus(true);
            
            JSONObject responseObject = new JSONObject();
            responseObject.put("todoId", todo.getId());
            responseDto.setResponse(responseObject);

            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Project not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateTodo(TodoDto updatedTodoDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(updatedTodoDto.getId());
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setDescription(updatedTodoDto.getDescription());
            todo.setStatus(Constants.PROJECT_STATUS_IN_PROGRESS);
            todo.setUpdatedDate(LocalDateTime.now());
            todoRepository.save(todo);

            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo updated successfully.");
            responseDto.setStatus(true);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteTodo(Integer pid, Integer tid) {
        Optional<Todo> optionalTodo = todoRepository.findByIdAndProject_Id(tid, pid);
        if (optionalTodo.isPresent()) {
            todoRepository.deleteById(optionalTodo.get().getId());

            // Creating response DTO
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo deleted successfully.");
            responseDto.setStatus(true);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<ResponseDto> markTodoAsComplete(Integer pid,Integer tid) {
        Optional<Todo> optionalTodo = todoRepository.findByIdAndProject_Id(tid, pid);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setStatus("completed");
            todo.setUpdatedDate(LocalDateTime.now());
            todoRepository.save(todo);

            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo marked as complete successfully.");
            responseDto.setStatus(true);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<ResponseDto> markTodoAsPending(Integer pid,Integer tid) {
        Optional<Todo> optionalTodo = todoRepository.findByIdAndProject_Id(tid, pid);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setStatus("pending");
            todo.setUpdatedDate(LocalDateTime.now());
            todoRepository.save(todo);

            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo marked as pending successfully.");
            responseDto.setStatus(true);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }
    

    @Override
    public ResponseEntity<ResponseDto> getTodoById(Integer pid, Integer tid) {
        Optional<Todo> optionalTodo = todoRepository.findByIdAndProject_Id(tid, pid);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            
            JSONObject todoJson = new JSONObject();
            todoJson.put("id", todo.getId());
            todoJson.put("description", todo.getDescription());
            todoJson.put("status", todo.getStatus());
            todoJson.put("createdDate", todo.getCreatedDate());
            todoJson.put("updatedDate", todo.getUpdatedDate());
            
            ResponseDto responseDto = new ResponseDto("Todo found.", todoJson, true);
            
            return ResponseEntity.ok().body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto("Todo not found.", null, false));
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateTodo(Integer pid, Integer tid, TodoDto updatedTodoDto) {
        Optional<Todo> optionalTodo = todoRepository.findByIdAndProject_Id(tid, pid);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setDescription(updatedTodoDto.getDescription());
            todo.setStatus(Constants.TODO_STATUS_PENDING);
            todo.setUpdatedDate(LocalDateTime.now());
            todoRepository.save(todo);

            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo updated successfully.");
            responseDto.setStatus(true);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Todo not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }


}
