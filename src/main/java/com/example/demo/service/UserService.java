package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.userRepo;

@Service
public class UserService {
	@Autowired
	private userRepo repo;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12); 
	
	public User saveUser(User user) {
		if(repo.existsByUsername(user.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public User getUserByUsername(String username) {
		User user=repo.findByUsername(username)
				.orElseThrow(()->new RuntimeException("User not found"));
		
		return user;
	}
	
	public List<User> getAllUsers(){
		return repo.findAll();
		
	}

}
