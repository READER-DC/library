package com.example.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public Book getBook(Long id) {
		Optional<Book> optional = bookRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new RuntimeException("Book not found");
	}
	
	public List<Book> getAllBook(){
		List<Book> dooks = new ArrayList<Book>();
		Iterator<Book> iterator = bookRepository.findAll().iterator();
		while (iterator.hasNext()){
			Book itemBook = iterator.next();
			dooks.add(itemBook);
		}
		return dooks;
	}
	
	public Book crerateBook(Book book) {
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}
	
	public Book editBook(Long id, Book book) {
		Book originBook = getBook(id);
		
		originBook.setAuthor(book.getAuthor());
		originBook.setDescription(book.getAuthor());
		originBook.setIsbn(book.getIsbn());
		originBook.setName(book.getName());
		originBook.setPublisher(book.getPublisher());
		originBook.setYear(book.getYear());
		
		Book updatedBook =  bookRepository.save(originBook);
		
		return updatedBook;
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

}
