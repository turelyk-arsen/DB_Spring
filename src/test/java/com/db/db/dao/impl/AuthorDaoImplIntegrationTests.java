package com.db.db.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.db.db.TestDataUtil;
import com.db.db.domain.Author;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTests {
	
	private AuthorDaoImpl underTest;
	
	@Autowired
	public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
		this.underTest = underTest;
	}

	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		Author author = TestDataUtil.createTestAuthor();
		underTest.create(author);
		Optional<Author> result = underTest.findOne(author.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
		
	}
}
