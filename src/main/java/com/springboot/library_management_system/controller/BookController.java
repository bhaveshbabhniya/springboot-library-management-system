package com.springboot.library_management_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.library_management_system.entity.Book;
import com.springboot.library_management_system.repository.BookRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "2. Books", description = "Books Upload & View APIs")
public class BookController {

	private final BookRepository bookRepository;
	
	@PreAuthorize("hasRole('LIBRARIAN')")
	@PostMapping("/upload")
	public ResponseEntity<?> uploadBooks(@RequestBody List<Book> books) {
		bookRepository.saveAll(books);
		return ResponseEntity.ok("Books Uploaded successfully");
	}
	
	@GetMapping
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
