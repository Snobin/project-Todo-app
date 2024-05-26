package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Project;
import com.example.demo.entity.Todo;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TodoRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public ResponseEntity<ResponseDto> createProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setTitle(projectDto.getTitle());
        project.setCreatedDate(LocalDateTime.now());
        projectRepository.save(project);

        // Creating response DTO
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Project created successfully.");
        responseDto.setStatus(true);
        responseDto.setResponse(null);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
//        System.out.println(projects.toString());
        // Creating response DTO
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Projects retrieved successfully.");
        responseDto.setStatus(true);
        JSONObject project= new JSONObject();
        project.put("Projects", projects);
        responseDto.setResponse(project);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ResponseDto> getTodosByProjectId(Integer projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            List<Todo> todos = todoRepository.findByProjectId(projectId);

            // Creating response DTO
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Project found.");
            responseDto.setStatus(true);

            JSONObject projectData = new JSONObject();
            projectData.put("id", project.getId());
            projectData.put("title", project.getTitle());
            projectData.put("createdDate", project.getCreatedDate());

            // Adding todos to the response
            JSONArray todosArray = new JSONArray();
            for (Todo todo : todos) {
                JSONObject todoData = new JSONObject();
                todoData.put("id", todo.getId());
                todoData.put("description", todo.getDescription());
                todoData.put("status", todo.getStatus());
                todoData.put("createdDate", todo.getCreatedDate());
                todoData.put("updatedDate", todo.getUpdatedDate());
                todosArray.add(todoData);
            }

            projectData.put("todos", todosArray);
            responseDto.setResponse(projectData);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            // Creating response DTO
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Project not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }



    @Override
    public ResponseEntity<ResponseDto> updateProject(ProjectDto updatedProjectDto,Integer id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setTitle(updatedProjectDto.getTitle());
            projectRepository.save(project);

            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Project updated successfully.");
            responseDto.setStatus(true);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Project not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteProject(Integer id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            projectRepository.deleteById(id);

            // Creating response DTO
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Project deleted successfully.");
            responseDto.setStatus(true);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        } else {
            // Creating response DTO
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Project not found.");
            responseDto.setStatus(false);
            responseDto.setResponse(null);

            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }
    public Project getProjectById(Integer projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        return optionalProject.orElse(null);
    }

	@Override
	public ResponseEntity<ResponseDto> getProjectByid(Integer projectId) {
		 Optional<Project> optionalProject = projectRepository.findById(projectId);
		    if (optionalProject.isPresent()) {
		        Project project = optionalProject.get();
		        
		        // Creating response DTO
		        ResponseDto responseDto = new ResponseDto();
		        responseDto.setMessage("Project found.");
		        responseDto.setStatus(true);
		        JSONObject projectData = new JSONObject();
		        projectData.put("id", project.getId());
		        projectData.put("title", project.getTitle());
		        projectData.put("createdDate", project.getCreatedDate());
		        responseDto.setResponse(projectData);

		        return new ResponseEntity<>(responseDto, HttpStatus.OK);
		    } else {
		        // Creating response DTO
		        ResponseDto responseDto = new ResponseDto();
		        responseDto.setMessage("Project not found.");
		        responseDto.setStatus(false);
		        responseDto.setResponse(null);

		        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
		    }
	}
}
