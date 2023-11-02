package com.db.db.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.db.db.dao.BookDAO;
import com.db.db.domain.Book;

public class BookDaoImpl implements BookDAO {
	
	public final JdbcTemplate jdbcTemplate;
	
	public BookDaoImpl (final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Book book) {
		jdbcTemplate.update(
				"INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
				book.getIsbn(), book.getTitle(), book.getAuthorId());
		
	}



}
