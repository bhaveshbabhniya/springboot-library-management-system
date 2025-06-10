package com.springboot.library_management_system.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.library_management_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.map(user -> new org.springframework.security.core.userdetails.User(
						user.getUsername(),
						user.getPassword(),
						user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList()
				))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}