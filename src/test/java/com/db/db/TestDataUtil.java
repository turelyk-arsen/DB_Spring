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
	
	public static Book createTestBook() {
		return Book.builder()
				.isbn("978-1-23")
				.title("The shadow")
				.authorId(1L)
				.build();
	}
	
}
