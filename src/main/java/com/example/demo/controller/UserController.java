package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.JwtService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtservice;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@GetMapping
	public List<User> getall(){
		return service.getAllUsers();
		
	}
	@PostMapping("login")
	public String login(@RequestBody User user) {
		Authentication authentication =authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtservice.generateToken(user.getUsername());
		}
		else {
			return "Login failed";
		}
	}
	

}
