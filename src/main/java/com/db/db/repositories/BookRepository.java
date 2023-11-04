package com.db.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.db.db.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

}
