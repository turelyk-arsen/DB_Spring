package com.db.db.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.db.db.TestDataUtil;
import com.db.db.dao.impl.BookDaoImpl;
import com.db.db.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private BookDaoImpl underTest;
	
	@Test
	public void testThatCreateBookGeneratesCorrectSql() {
		Book book = TestDataUtil.createTestBook();
		
		underTest.create(book);
		
		verify(jdbcTemplate).update(
				eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
				eq("978-1-23"),
				eq("The shadow"),
				eq(1L)
				);
	}

	
	@Test
	public void testThatFindOneBookGeneratesCorrectSql() {
		underTest.findOne("978-1-23");
		verify(jdbcTemplate).query(
				eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
				ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
				eq("978-1-23"));
	}
	
	@Test
	public void testThatFindGeneratesCorrectSql() {
		underTest.find();
		verify(jdbcTemplate).query(
				eq("SELECT isbn, title, author_id FROM books"),
				ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
	}
	
	@Test
	public void testThatUpdateGeneratesCorrectSql() {
		Book book = TestDataUtil.createTestBook();
		underTest.update("978-1-23", book);
		
		verify(jdbcTemplate).update(
				"UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?", 
				"978-1-23", "The shadow", 1L, "978-1-23");
	}
	
	@Test
	public void testThatDeleteGeneratesCorrectSql() {
		underTest.delete("978-1-23");
		verify(jdbcTemplate).update("DELETE FROM books WHERE isbn = ?", "978-1-23");
	}
	
	

}
