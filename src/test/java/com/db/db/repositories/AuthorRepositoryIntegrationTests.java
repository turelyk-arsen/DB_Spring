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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {
	
	private AuthorRepository underTest;
	
	@Autowired
	public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
		this.underTest = underTest;
	}

	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		AuthorEntity author = TestDataUtil.createTestAuthor();
		underTest.save(author);
		Optional<AuthorEntity> result = underTest.findById(author.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
		
	}
	
	@Test
	public void testThatMultiplateAuthorsCanBeCreatedAndRecalled () {
		AuthorEntity authorA = TestDataUtil.createTestAuthor();
		underTest.save(authorA);
		AuthorEntity authorB = TestDataUtil.createTestAuthorA();
		underTest.save(authorB);
		AuthorEntity authorC = TestDataUtil.createTestAuthorB();
		underTest.save(authorC);
		
		Iterable<AuthorEntity> result = underTest.findAll();
		assertThat(result).hasSize(3)
			.containsExactly(authorA, authorB, authorC);
	}
	
	@Test
	public void testThatAuthorCanBeUpdated () {
		AuthorEntity author = TestDataUtil.createTestAuthor();
		underTest.save(author);
		author.setName("UPDATED");
		
		underTest.save(author);
		Optional<AuthorEntity> result = underTest.findById(author.getId());
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}
	
	@Test
	public void testThatAuthorCanBeDeleted() {
		AuthorEntity author = TestDataUtil.createTestAuthor();
		underTest.save(author);
		
		underTest.deleteById(author.getId());
		
		Optional<AuthorEntity> result = underTest.findById(author.getId());
		assertThat(result).isEmpty();
	}
	
	@Test
	public void testThatGetAuthorWithAgeLessThan() {
		AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
		underTest.save(testAuthorA);
		AuthorEntity testAuthorB = TestDataUtil.createTestAuthorA();
		underTest.save(testAuthorB);
		AuthorEntity testAuthorC = TestDataUtil.createTestAuthorB();
		underTest.save(testAuthorC);
		
		Iterable<AuthorEntity> result = underTest.ageLessThan(50);
		assertThat(result).containsExactly(testAuthorB, testAuthorC);
	}
	
	@Test
	public void testThatGetAuthorsWithAgeGreaterThan() {
		AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
		underTest.save(testAuthorA);
		AuthorEntity testAuthorB = TestDataUtil.createTestAuthorA();
		underTest.save(testAuthorB);
		AuthorEntity testAuthorC = TestDataUtil.createTestAuthorB();
		underTest.save(testAuthorC);
		
		Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(50);
		assertThat(result).containsExactly(testAuthorA);
	}
}
