package com.db.db;

import com.db.db.domain.entities.AuthorEntity;
import com.db.db.domain.entities.BookEntity;

public final class TestDataUtil {
	
	private TestDataUtil() {
		
	}
	
	public static AuthorEntity createTestAuthor() {
		return AuthorEntity.builder()
				.id(1L)
				.name("Rose")
				.age(80)
				.build();
	}
	
	public static AuthorEntity createTestAuthorA() {
		return AuthorEntity.builder()
				.id(2L)
				.name("Tomas Croin")
				.age(44)
				.build();
	}
	
	public static AuthorEntity createTestAuthorB() {
		return AuthorEntity.builder()
				.id(3L)
				.name("Jesse a Casey")
				.age(24)
				.build();
	}
	
	public static BookEntity createTestBook(final AuthorEntity author) {
		return BookEntity.builder()
				.isbn("978-1-23")
				.title("The shadow")
				.author(author)
				.build();
	}
	public static BookEntity createTestBookA(final AuthorEntity author) {
		return BookEntity.builder()
				.isbn("978-1-55")
				.title("Beyond the horizon")
				.author(author)
				.build();
	}
	public static BookEntity createTestBookB(final AuthorEntity author) {
		return BookEntity.builder()
				.isbn("978-1-76")
				.title("The last ember")
				.author(author)
				.build();
	}
	
}
