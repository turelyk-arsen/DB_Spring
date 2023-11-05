package com.db.db.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.db.domain.Book;
import com.db.db.domain.NewBook;

import lombok.extern.java.Log;

@RestController
@Log
public class BookController {
	
	@GetMapping(path = "/books")
	public NewBook retrieveBook() {
		return NewBook.builder()
				.isbn("978-12-2332")
				.title("The Enigma of Eternity")
				.author("Aria Montgomery")
				.yearPublished("2005")
				.build();
	}
	
	@PostMapping(path = "/books")
	public NewBook createBook(@RequestBody final NewBook book) {
		log.info("Get book: " + book.toString());
		return book;
	}

}
