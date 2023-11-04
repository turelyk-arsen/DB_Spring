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
import com.db.db.domain.Author;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
	
	@Test
	public void testThatMultiplateAuthorsCanBeCreatedAndRecalled () {
		Author authorA = TestDataUtil.createTestAuthor();
		underTest.create(authorA);
		Author authorB = TestDataUtil.createTestAuthorA();
		underTest.create(authorB);
		Author authorC = TestDataUtil.createTestAuthorB();
		underTest.create(authorC);
		
		List<Author> result = underTest.find();
		assertThat(result).hasSize(3)
			.containsExactly(authorA, authorB, authorC);
	}
	
	@Test
	public void testThatAuthorCanBeUpdated () {
		Author author = TestDataUtil.createTestAuthor();
		underTest.create(author);
		author.setName("UPDATED");
		
		underTest.update(author.getId(), author);
		Optional<Author> result = underTest.findOne(author.getId());
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}
	
	@Test
	public void testThatAuthorCanBeDeleted() {
		Author author = TestDataUtil.createTestAuthor();
		underTest.create(author);
		
		underTest.delete(author.getId());
		
		Optional<Author> result = underTest.findOne(author.getId());
		assertThat(result).isEmpty();
	}
}
