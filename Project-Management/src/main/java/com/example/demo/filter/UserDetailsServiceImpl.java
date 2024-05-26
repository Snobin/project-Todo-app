package com.example.demo.filter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dto.CustomUserDetails;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AuthRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    private AuthRepository repo;

    public UserDetailsServiceImpl(AuthRepository repo) {
        this.repo = repo;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        return new CustomUserDetails(userEntity.getUsername(), userEntity.getPassword());
    }
}
