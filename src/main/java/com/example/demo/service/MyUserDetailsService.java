package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.userPrincipal;
import com.example.demo.repo.userRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private userRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByUsername(username)
				.orElseThrow(()-> new RuntimeException("No user found with that details !!"));
		return new userPrincipal(user);
	}
	
	

}
