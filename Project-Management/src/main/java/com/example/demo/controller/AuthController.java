package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDTO;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupDTO signupDTO) {
		return authService.addUser(signupDTO);
	}

	@PostMapping("/login")
	public ResponseEntity<?> LoginandGenerate(@Valid @RequestBody LoginDTO dto) {

		return authService.LoginandGenerate(dto);


	}
}
