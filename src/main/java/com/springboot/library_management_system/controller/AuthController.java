package com.springboot.library_management_system.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.library_management_system.dto.AuthRequest;
import com.springboot.library_management_system.entity.Role;
import com.springboot.library_management_system.entity.User;
import com.springboot.library_management_system.repository.UserRepository;
import com.springboot.library_management_system.security.JwtService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "1. Authentication", description = "Login & Registration APIs")
public class AuthController {

	private final UserRepository userRepository;
	private final AuthenticationManager authManager;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody AuthRequest request) {
		User user = User.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.roles(Set.of(Role.MEMBER))
				.build();
		userRepository.save(user);
		return ResponseEntity.ok("User Registered");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
			String token = jwtService.generateToken(user.getUsername(), user.getRoles());
			return ResponseEntity.ok(Collections.singletonMap("token", token));
		}catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}
}
