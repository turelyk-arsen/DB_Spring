package com.db.db.books;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.db.db.domain.NewBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTests {
	
	@Test
	public void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		NewBook book = NewBook.builder()
				.isbn("978-12-2332")
				.title("The Enigma of Eternity")
				.author("Aria Montgomery")
				.yearPublished("2005")
				.build();
		
		String result = objectMapper.writeValueAsString(book);
		assertThat(result).isEqualTo("{\"isbn\":\"978-12-2332\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"year\":\"2005\"}");
	}
	
	@Test
	public void testThatObjectMapperCanCreateJavaObjectFromJsonObject () throws JsonProcessingException {
		NewBook book = NewBook.builder()
				.isbn("978-12-2332")
				.title("The Enigma of Eternity")
				.author("Aria Montgomery")
				.yearPublished("2005")
				.build();
		String json = "{\"isbn\":\"978-12-2332\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"year\":\"2005\"}";
		
		final ObjectMapper objectMapper = new ObjectMapper();
		NewBook result = objectMapper.readValue(json, NewBook.class);
		assertThat(result).isEqualTo(book);
		
	}

}
