package com.springboot.library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.library_management_system.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
