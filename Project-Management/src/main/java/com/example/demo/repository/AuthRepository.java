package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {


	List<UserEntity> findAll();

	Optional<UserEntity> findByUsername(String username);

}