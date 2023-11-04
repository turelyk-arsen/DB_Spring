package com.db.db.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.db.db.TestDataUtil;
import com.db.db.dao.AuthorDAO;
import com.db.db.domain.Author;
import com.db.db.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
		Optional<Book> result = underTest.findOne(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
		
	}
	
	@Test
	public void testThatMultipleBooksCanBeCreatedAndRecalled() {
		Author author = TestDataUtil.createTestAuthor();
		authorDao.create(author);
		
		Book bookA = TestDataUtil.createTestBook();
		bookA.setAuthorId(author.getId());
		underTest.create(bookA);
		
		Book bookB = TestDataUtil.createTestBookA();
		bookB.setAuthorId(author.getId());
		underTest.create(bookB);
		
		Book bookC = TestDataUtil.createTestBookB();
		bookC.setAuthorId(author.getId());
		underTest.create(bookC);
		
		List<Book> result = underTest.find();
		assertThat(result)
			.hasSize(3)
			.containsExactly(bookA, bookB, bookC);
	}
	
	@Test
	public void testThatBookCanBeUpdated () {
		Author author = TestDataUtil.createTestAuthor();
		authorDao.create(author);
		
		Book book = TestDataUtil.createTestBook();
		book.setAuthorId(author.getId());
		underTest.create(book);
		
		book.setTitle("UPDATE");
		underTest.update(book.getIsbn(), book);
		
		Optional<Book> result = underTest.findOne(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}
	
	@Test
	public void testThatCanBeDeleted() {
		Author author = TestDataUtil.createTestAuthor();
		authorDao.create(author);
		
		Book book = TestDataUtil.createTestBook();
		book.setAuthorId(author.getId());
		underTest.create(book);
		
		underTest.delete(book.getIsbn());
		
		Optional<Book> result = underTest.findOne(book.getIsbn());
		assertThat(result).isEmpty();
	}
}
