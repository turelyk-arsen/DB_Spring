package com.db.db.dao.impl;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.db.db.TestDataUtil;
import com.db.db.dao.impl.AuthorDaoImpl;
import com.db.db.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

		@Mock
		private JdbcTemplate jdbcTemplate;
		
		@InjectMocks
		private AuthorDaoImpl underTest;
		
		@Test
		public void testThatCreateAuthorGeneratesCorrectSql() {
			Author author = TestDataUtil.createTestAuthor();
			underTest.create(author);
			verify(jdbcTemplate).update(
					eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
					eq(1L), eq("Rose"), eq(80));
			
		}


		
		@Test
		public void testThatFindOneGeneratesTheCorrectSql() {
			underTest.findOne(1L);
			verify(jdbcTemplate).query(
					eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"), 
					ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
					eq(1L));
			
		}
		
		@Test
		public void testThatFindManyGeneratesCorrectSql() {
			underTest.find();
			verify(jdbcTemplate).query(
					eq("SELECT id, name, age FROM authors"),
					ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
		}
		
		@Test
		public void testThatUpdateGeneratesCorrectSql() {
			Author author = TestDataUtil.createTestAuthor();
			underTest.update(author.getId(), author);
			
			verify(jdbcTemplate).update(
					"UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
					1L, "Rose", 80, 1L);
		}
		
		@Test
		public void testThatDeleteGeneratesTheCorrectSql() {
			underTest.delete(1L);
			verify(jdbcTemplate).update("DELETE FROM authors WHERE id = ?", 1L);
		}
}
