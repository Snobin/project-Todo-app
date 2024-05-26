package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;


    @Column(name = "password", nullable = false)
    private String password;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
