package com.db.db.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.db.db.TestDataUtil;
import com.db.db.dao.AuthorDAO;
import com.db.db.domain.Author;
import com.db.db.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTests {

	private BookDaoImpl underTest;
	private AuthorDAO authorDao;
	
	@Autowired
	public BookDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDAO authorDao) {
		this.underTest = underTest;
		this.authorDao = authorDao;
	}
	
	@Test
	public void testThatBookCanBeCreatedAndCalled() {
		Author author = TestDataUtil.createTestAuthor();
		authorDao.create(author);
		
		Book book = TestDataUtil.createTestBook();
		book.setAuthorId(author.getId());
		underTest.create(book);
		Optional<Book> result = underTest.find(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
		
	}
}
