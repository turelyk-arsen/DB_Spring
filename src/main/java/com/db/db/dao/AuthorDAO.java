package com.db.db.dao;

import java.util.List;
import java.util.Optional;

import com.db.db.domain.Author;

public interface AuthorDAO {

	void create (Author author);
	
	Optional<Author> findOne(long l);
	
	List<Author> find();
	
	void update(long id, Author author);
	
	void delete(long id);
}
