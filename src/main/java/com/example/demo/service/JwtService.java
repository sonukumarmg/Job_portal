package com.example.demo.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	public String  generateToken(String username) {
		Map<String,Object> claims=new HashMap<>();
		return Jwts.builder()
				.addClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
				.signWith(getkey(), SignatureAlgorithm.HS256).compact();
		
	}

	private Key getkey() {
		return Keys.hmacShaKeyFor("my-super-secret-key-for-jwt-that-is-at-least-32-characters"
				.getBytes());
	}
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
		
	}
	
	private <T>T extractClaim(String token,Function<Claims,T> claimResolver){
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getkey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean validateToken(String token,UserDetails userdetails) {
		final String username=extractUsername(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
		
	}

	private boolean isTokenExpired(String token) {
		return (extractexpiration(token).before(new Date()));
	}

	private Date extractexpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}
	
	

