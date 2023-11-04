package com.db.db.dao;

import java.util.Optional;

import com.db.db.domain.Book;

public interface BookDAO {

		void create(Book book);
		
		Optional<Book> find(String isbn);
}
