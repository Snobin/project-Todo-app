package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Project;
public interface ProjectService {


    ResponseEntity<ResponseDto> createProject(ProjectDto projectDto);

    ResponseEntity<ResponseDto> getAllProjects();

    public ResponseEntity<ResponseDto> getTodosByProjectId(Integer projectId);
    
    
    ResponseEntity<ResponseDto> updateProject( ProjectDto updatedProjectDto,Integer id);

    ResponseEntity<ResponseDto> deleteProject(Integer id);
    public Project getProjectById(Integer projectId) ;
    
    ResponseEntity<ResponseDto> getProjectByid(Integer projectId) ;

}
