package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Project {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private LocalDateTime createdDate;
    private String createdBy;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)    
    @JsonManagedReference
    private List<Todo> todos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	

	public Project(Integer id, String title, LocalDateTime createdDate, String createdBy, List<Todo> todos) {
		super();
		this.id = id;
		this.title = title;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.todos = todos;
	}

	public Project() {
		super();
	}

	

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", todos=" + todos + "]";
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
    
}

