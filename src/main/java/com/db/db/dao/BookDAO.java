package com.db.db.dao;

import java.util.List;
import java.util.Optional;

import com.db.db.domain.Book;

public interface BookDAO {

		void create(Book book);
		
		Optional<Book> findOne(String isbn);
		
		List<Book> find();
		
		void update(String isbn, Book book);
		
		void delete(String isbn);
}
