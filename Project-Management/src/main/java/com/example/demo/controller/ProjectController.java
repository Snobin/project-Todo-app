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

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Project;
import com.example.demo.service.GistService;
import com.example.demo.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private GistService gistService;

	@PostMapping
	public ResponseEntity<ResponseDto> createProject(@RequestBody @Valid ProjectDto projectDto) {
		return projectService.createProject(projectDto);
	}

	@GetMapping
	public ResponseEntity<ResponseDto> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping("/getProject/{id}")
	public ResponseEntity<ResponseDto> getProjectById(@PathVariable Integer id) {
		return projectService.getProjectByid(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getTodosByProjectId(@PathVariable Integer id) {
		return projectService.getTodosByProjectId(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDto> updateProject(@RequestBody @Valid ProjectDto updatedProjectDto,
			@PathVariable("id") Integer id) {
		return projectService.updateProject(updatedProjectDto, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDto> deleteProject(@PathVariable Integer id) {
		return projectService.deleteProject(id);
	}

	@GetMapping("/{projectId}/export-gist")
	public ResponseEntity<ResponseDto> exportProjectSummaryAsGist(@PathVariable("projectId") Integer projectId) {
		Project project = projectService.getProjectById(projectId);
		return gistService.exportProjectSummaryAsGist(project);
	}

}
