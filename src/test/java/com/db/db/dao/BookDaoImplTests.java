package com.db.db.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

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
		Book book = Book.builder()
				.isbn("978-1-23")
				.title("The shadow")
				.authorId(1L)
				.build();
		
		underTest.create(book);
		
		verify(jdbcTemplate).update(
				eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
				eq("978-1-23"),
				eq("The shadow"),
				eq(1L)
				);
	}
	
	

}
