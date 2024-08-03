package com.example.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	
	@GetMapping("/book")
	public String getAllBook() {
		return "This is book";
	}
	
	@GetMapping("/book/{id}")
	public String getBookbyId(@PathVariable Long id) {
		return "This is book: " + id;
	}
	
	@PostMapping("/book")
	public String createBook() {
		return "Book created!";
	}
	
	@PutMapping("/book/{id}")
	public String editBook(@PathVariable Long id) {
		return "Book id: " + id +  " edited";
	}
	
	@DeleteMapping("/book/{id}")
	public String  deleteBook (@PathVariable Long id) {
		return "Book id: " + id +  " deleted";
	}

}
