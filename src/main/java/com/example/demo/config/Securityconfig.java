package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Securityconfig {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	@Bean
	public AuthenticationProvider authprovider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}//we are just defining here the provider ,spring will automatically manage the authentication process using this authentication provider.
	
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
		
		http.csrf(customizer->customizer.disable());
		http.authorizeHttpRequests(request->request
				.requestMatchers("/api/users/register","/api/users/login")
				.permitAll()
				.anyRequest().authenticated());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//THIS IS HARDCODING THE USER.ABOVE WE ARE CONNECTING IT TO DATABASE SO THAT THE USER FROM DATABSE GET AUTHENTICATED.
    /*
	@Bean
	public UserDetailsService userdetailsservice() {
		
		UserDetails user=User
				.withDefaultPasswordEncoder()
				.username("Sonu")
				.password("1234")
				.roles("USER")
				.build();
	
		UserDetails admin=User
				.withDefaultPasswordEncoder()
				.username("Admin")
				.password("1234")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user,admin);
		
	}
	*/
	

}

