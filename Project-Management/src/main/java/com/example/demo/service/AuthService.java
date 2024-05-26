package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dto.CustomUserDetails;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDTO;

public interface AuthService {

	public ResponseEntity<?> addUser(SignupDTO dto);

	public boolean checkemailpassword(LoginDTO ldto);

	public List<SignupDTO> getAllUsers();
	
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}