package com.example.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Book;

@Service
public class BookService {
	
	public Book getBook(Long id) {
		return new Book();
	}
	
	public List<Book> getAllBook(){
		return Collections.emptyList();
	}
	
	public Book crerateBook(Book book) {
		return book;
	}
	
	public Book editBook(Long id, Book book) {
		return book;
	}
	
	public void deleteBook(Long id) {
		
	}

}
