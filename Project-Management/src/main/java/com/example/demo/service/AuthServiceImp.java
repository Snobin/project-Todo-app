package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomUserDetails;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SignupDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AuthRepository;

@Service
public class AuthServiceImp implements AuthService {
	private static Logger logger = LogManager.getLogger(AuthServiceImp.class);

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private AuthRepository repo;

	

public ResponseEntity<ResponseDto> addUser(SignupDTO dto) {
    Optional<UserEntity> existingUserOptional = repo.findByUsername(dto.getEmail());
    if (existingUserOptional.isPresent()) {
        ResponseDto responseDto = new ResponseDto("User with this email already exists", null, false);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    UserEntity entity = new UserEntity();
    try {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(encodedPassword);
        entity.setUsername(dto.getEmail());
        repo.save(entity);
        ResponseDto responseDto = new ResponseDto("Successfully Inserted", null, true);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    } catch (Exception e) {
        logger.error("Error:" + e.getMessage(), e);
        ResponseDto responseDto = new ResponseDto("Exception Occurred", null, false);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
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
				return new CustomUserDetails(user.getUsername(), user.getPassword());
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