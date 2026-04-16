package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface userRepo extends JpaRepository<User, Integer>{
	public Optional<User> findByUsername(String username);
	boolean existsByUsername(String username); //to check if username is already taken.
	
	List<User> findByRole(String role);//to get all the user of given role.

}
