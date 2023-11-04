package com.db.db;

import com.db.db.domain.Author;
import com.db.db.domain.Book;

public final class TestDataUtil {
	
	private TestDataUtil() {
		
	}
	
	public static Author createTestAuthor() {
		return Author.builder()
				.id(1L)
				.name("Rose")
				.age(80)
				.build();
	}
	
	public static Author createTestAuthorA() {
		return Author.builder()
				.id(2L)
				.name("Tomas Croin")
				.age(44)
				.build();
	}
	
	public static Author createTestAuthorB() {
		return Author.builder()
				.id(3L)
				.name("Jesse a Casey")
				.age(24)
				.build();
	}
	
	public static Book createTestBook(final Author author) {
		return Book.builder()
				.isbn("978-1-23")
				.title("The shadow")
				.author(author)
				.build();
	}
	public static Book createTestBookA(final Author author) {
		return Book.builder()
				.isbn("978-1-55")
				.title("Beyond the horizon")
				.author(author)
				.build();
	}
	public static Book createTestBookB(final Author author) {
		return Book.builder()
				.isbn("978-1-76")
				.title("The last ember")
				.author(author)
				.build();
	}
	
}
