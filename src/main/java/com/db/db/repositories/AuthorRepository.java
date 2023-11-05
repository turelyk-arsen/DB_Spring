package com.db.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.db.db.domain.entities.AuthorEntity;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

	Iterable<AuthorEntity> ageLessThan(int age);

	@Query("SELECT a from Author a where a.age > ?1")
	Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);

}
