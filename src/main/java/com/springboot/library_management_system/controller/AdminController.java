package com.springboot.library_management_system.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.library_management_system.entity.Role;
import com.springboot.library_management_system.entity.User;
import com.springboot.library_management_system.repository.UserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "3. Admin", description = "User Promotion & Demotion APIs")
public class AdminController {

	private final UserRepository userRepository;
	
	@PutMapping("/promote/{username}")
	@PreAuthorize("hasRole('LIBRARIAN')")
	public ResponseEntity<String> promoteUser(@PathVariable String username) {
		Optional<User> userOpt = userRepository.findByUsername(username);
		if(userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		User user = userOpt.get();
		
		if(user.getRoles().contains(Role.LIBRARIAN)) {
			return ResponseEntity.badRequest().body("User is already a LIBRARIAN");
		}
		
		user.getRoles().clear();
		user.getRoles().add(Role.LIBRARIAN);
		userRepository.save(user);
		return ResponseEntity.ok("User promoted to LIBRARIAN");
	}
	
	@PutMapping("/demote/{username}")
	@PreAuthorize("hasRole('LIBRARIAN')")
	public ResponseEntity<String> demoteUser(@PathVariable String username) {
		Optional<User> userOpt = userRepository.findByUsername(username);
		if(userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		User user = userOpt.get();
		
		if(user.getRoles().contains(Role.MEMBER)) {
			return ResponseEntity.badRequest().body("User is already a MEMBER");
		}
		
		user.getRoles().clear();
		user.getRoles().add(Role.MEMBER);
		userRepository.save(user);
		return ResponseEntity.ok("User demoted to MEMBER");
	}
}
