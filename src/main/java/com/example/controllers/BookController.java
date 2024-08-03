package com.example.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Book;
import com.example.services.BookService;

@RestController
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/book")
	public Page<Book> getAllBook(@RequestParam Integer page, @RequestParam Integer size,
			@RequestParam(required = false) String query) {

		Pageable pageable = PageRequest.of(page, size);
		return bookService.getAllBook(query, pageable);
	}

	@GetMapping("/book/{id}")
	public Book getBookbyId(@PathVariable Long id) {
		return bookService.getBook(id);
	}

	@PostMapping("/book")
	public Book createBook(@RequestBody Book book) {
		return bookService.crerateBook(book);
	}

	@PutMapping("/book/{id}")
	public Book editBook(@PathVariable Long id, @RequestBody Book book) {
		return bookService.editBook(id, book);
	}

	@DeleteMapping("/book/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

}
