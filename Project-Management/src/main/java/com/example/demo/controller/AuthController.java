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
<<<<<<< HEAD
		return authService.LoginandGenerate(dto);
=======
		try {
			if (authService.checkemailpassword(dto)) {
				Optional<UserEntity> username = authRepository.findByUsername(dto.getEmail());
				UserEntity obj = username.get();
				final CustomUserDetails userDetails = authService.loadUserByUsername(obj.getUsername());
				System.out.println(userDetails.toString());
				String token = jwtUtil.generateToken(userDetails);
				JwtResponseDTO jwt = new JwtResponseDTO();
				jwt.setToken(token);
				jwt.setUsername(userDetails.getUsername());
				return new ResponseEntity<>(jwt, HttpStatus.OK);
			} else {
				
				return new ResponseEntity<>("Email or Password is Incorrect", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error during login", HttpStatus.INTERNAL_SERVER_ERROR);
		}
>>>>>>> 6fa0a469838150ea3d5bea71eb9d8730f1db0867
	}
}
