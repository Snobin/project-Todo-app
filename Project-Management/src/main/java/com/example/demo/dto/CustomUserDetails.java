package com.example.demo.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserEntity;

public class CustomUserDetails implements UserDetails {
	private String username;

	private String password;

	public CustomUserDetails(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public CustomUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public CustomUserDetails(UserEntity details) {
	}

	@Override
	public String toString() {
		return "CustomUserDetails [username=" + username + ", password=" + password + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}