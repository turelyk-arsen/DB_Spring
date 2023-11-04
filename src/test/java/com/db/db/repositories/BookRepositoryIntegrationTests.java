package com.db.db.repositories;

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
import com.db.db.domain.Author;
import com.db.db.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

	private BookRepository underTest;
	
	@Autowired
	public BookRepositoryIntegrationTests(BookRepository underTest) {
		this.underTest = underTest;
	}
	
	@Test
	public void testThatBookCanBeCreatedAndCalled() {
		Author author = TestDataUtil.createTestAuthor();
		
		Book book = TestDataUtil.createTestBook(author);
		underTest.save(book);
		Optional<Book> result = underTest.findById(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
		
	}
	
	@Test
	public void testThatMultipleBooksCanBeCreatedAndRecalled() {
		Author author = TestDataUtil.createTestAuthor();
		
		Book bookA = TestDataUtil.createTestBook(author);
		underTest.save(bookA);
		
		Book bookB = TestDataUtil.createTestBookA(author);
		underTest.save(bookB);
		
		Book bookC = TestDataUtil.createTestBookB(author);
		underTest.save(bookC);
		
		Iterable<Book> result = underTest.findAll();
		assertThat(result)
			.hasSize(3)
			.containsExactly(bookA, bookB, bookC);
	}
	
	@Test
	public void testThatBookCanBeUpdated () {
		Author author = TestDataUtil.createTestAuthor();
		
		Book book = TestDataUtil.createTestBook(author);
		underTest.save(book);
		
		book.setTitle("UPDATE");
		underTest.save(book);
		
		Optional<Book> result = underTest.findById(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}
	
	@Test
	public void testThatCanBeDeleted() {
		Author author = TestDataUtil.createTestAuthor();
		
		Book book = TestDataUtil.createTestBook(author);
		underTest.save(book);
		
		underTest.deleteById(book.getIsbn());
		
		Optional<Book> result = underTest.findById(book.getIsbn());
		assertThat(result).isEmpty();
	}
}
