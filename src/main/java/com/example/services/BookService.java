package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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

	public Page<Book> getAllBook(String query, Pageable pageable) {

		Page<Book> page = bookRepository.findByQuery("%" + query.toLowerCase() + "%", pageable);

		return page;
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

		Book updatedBook = bookRepository.save(originBook);

		return updatedBook;
	}

	public void deleteBook(Long id) {

		bookRepository.deleteById(id);
	}

}
