package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class ProjectDto {

	private Integer id;
	@NotEmpty(message = "{validation.projectdto.title}")
	private String title;

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

	@Override
	public String toString() {
		return "ProjectDto [id=" + id + ", title=" + title + "]";
	}


	
}
