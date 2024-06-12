package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Project;
import com.example.demo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	List<Todo> findByProject(Project project);

	List<Todo> findByProjectId(Integer projectId);

	Optional<Todo> findByIdAndProject_Id(Integer id, Integer projectId);

}
