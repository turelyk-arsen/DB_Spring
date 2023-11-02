package com.db.db.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.db.db.dao.AuthorDAO;
import com.db.db.domain.Author;

public class AuthorDaoImpl implements AuthorDAO {

		public final JdbcTemplate jdbcTemplate;
		
		public AuthorDaoImpl (final JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

		@Override
		public void create(Author author) {
			jdbcTemplate.update(
					"INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
					author.getId(), author.getName(), author.getAge());
		}


}
