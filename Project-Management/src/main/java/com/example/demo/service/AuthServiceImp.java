package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CustomUserDetails;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AuthRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class AuthServiceImp implements AuthService {
	private static Logger logger = LogManager.getLogger(AuthServiceImp.class);

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private AuthRepository repo;

	public ResponseEntity<?> addUser(SignupDTO dto) {
		UserEntity entity = new UserEntity();
		try {

			String encodedPassword = passwordEncoder.encode(dto.getPassword());
			entity.setPassword(encodedPassword);
			entity.setUsername(dto.getEmail());
			repo.save(entity);
			return new ResponseEntity<>("Successfully Inserted", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage(), e);
			return new ResponseEntity<>("Exception Occured", HttpStatus.OK);

		}

	}

	public ResponseEntity<?> updateAdmin(SignupDTO dto) {
//		String role = dto.getRole() == "false" ? "USER" : "ADMIN";
		UserEntity entity = new UserEntity();
		try {
			Optional<UserEntity> obj = repo.findByUsername(dto.getUsername());
			if (obj.isPresent()) {
				entity = obj.get();

				entity.setUsername(dto.getUsername());
				repo.save(entity);
			}
			return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage(), e);
			return new ResponseEntity<>("Exception Occured", HttpStatus.OK);

		}

	}

	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			Optional<UserEntity> opt = repo.findByUsername(username);

			if (opt.isEmpty()) {
				throw new UsernameNotFoundException("User with username: " + username + " not found!");
			} else {
				UserEntity user = opt.get();
//				Set<SimpleGrantedAuthority> authorities = Collections
//						.singleton(new SimpleGrantedAuthority(user.getRole()));
				return new CustomUserDetails( user.getUsername(), user.getPassword());
			}
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage(), e);
			return null;
		}

	}

	public boolean checkemailpassword(LoginDTO ldto) {
		try {
			Optional<UserEntity> opt = repo.findByUsername(ldto.getEmail());
			if (opt.isEmpty()) {
				return false;
			} else {
				UserEntity user = opt.get();
				if (user.getUsername().equals(ldto.getEmail())
						&& passwordEncoder.matches(ldto.getPassword(), user.getPassword())) {
					return true;
				}
				return false;

			}
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage(), e);
			return false;
		}

	}




	public List<SignupDTO> getAllUsers() {
		try {
			List<UserEntity> users = repo.findAll();
			return users.stream().map(this::convertToDto).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	private SignupDTO convertToDto(UserEntity user) {
		try {
			SignupDTO userDto = new SignupDTO();
		
			userDto.setUsername(user.getUsername());
			return userDto;
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage(), e);
			return null;
		}
	}

	

}