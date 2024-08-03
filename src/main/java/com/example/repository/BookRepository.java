package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>, CrudRepository<Book, Long> {

	@Query("SELECT book FROM Book book WHERE lower(book.name) like :query" 
			+ " or lower(book.author) like :query"
			+ " or lower(book.description) like :query" 
			+ " or lower(book.isbn) like :query")
	Page<Book> findByQuery(String query, Pageable pageable);

}
