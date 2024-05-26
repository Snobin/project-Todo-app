package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class TodoDto {

	private Integer Id;
	@NotEmpty(message = "{validation.tododto.description}")
	private String description;
	
	private Integer projectId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "TodoDto [Id=" + Id + ", description=" + description + ", projectId=" + projectId + "]";
	}

}
