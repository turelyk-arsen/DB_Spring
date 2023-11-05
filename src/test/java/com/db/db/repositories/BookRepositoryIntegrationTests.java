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
import com.db.db.domain.entities.AuthorEntity;
import com.db.db.domain.entities.BookEntity;

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
		AuthorEntity author = TestDataUtil.createTestAuthor();
		
		BookEntity book = TestDataUtil.createTestBook(author);
		underTest.save(book);
		Optional<BookEntity> result = underTest.findById(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
		
	}
	
	@Test
	public void testThatMultipleBooksCanBeCreatedAndRecalled() {
		AuthorEntity author = TestDataUtil.createTestAuthor();
		
		BookEntity bookA = TestDataUtil.createTestBook(author);
		underTest.save(bookA);
		
		BookEntity bookB = TestDataUtil.createTestBookA(author);
		underTest.save(bookB);
		
		BookEntity bookC = TestDataUtil.createTestBookB(author);
		underTest.save(bookC);
		
		Iterable<BookEntity> result = underTest.findAll();
		assertThat(result)
			.hasSize(3)
			.containsExactly(bookA, bookB, bookC);
	}
	
	@Test
	public void testThatBookCanBeUpdated () {
		AuthorEntity author = TestDataUtil.createTestAuthor();
		
		BookEntity book = TestDataUtil.createTestBook(author);
		underTest.save(book);
		
		book.setTitle("UPDATE");
		underTest.save(book);
		
		Optional<BookEntity> result = underTest.findById(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}
	
	@Test
	public void testThatCanBeDeleted() {
		AuthorEntity author = TestDataUtil.createTestAuthor();
		
		BookEntity book = TestDataUtil.createTestBook(author);
		underTest.save(book);
		
		underTest.deleteById(book.getIsbn());
		
		Optional<BookEntity> result = underTest.findById(book.getIsbn());
		assertThat(result).isEmpty();
	}
}
