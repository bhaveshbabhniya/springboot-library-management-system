package com.springboot.library_management_system.security;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springboot.library_management_system.entity.Role;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expirationMs}")
	private long expirationMs;
	
	private Key getKey() {
		byte[] keyBytes = secret.getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String username, Set<Role> roles) {
		return Jwts.builder()
				.setSubject(username)
				.claim("roles",roles.stream().map(Enum::name).collect(Collectors.toList()))
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
			return true;
		}catch (JwtException e) {
			return false;
		}
	}
}
