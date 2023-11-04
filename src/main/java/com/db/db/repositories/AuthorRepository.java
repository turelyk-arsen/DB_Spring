package com.db.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.db.db.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

	Iterable<Author> ageLessThan(int age);

	@Query("SELECT a from Author a where a.age > ?1")
	Iterable<Author> findAuthorsWithAgeGreaterThan(int age);

}
