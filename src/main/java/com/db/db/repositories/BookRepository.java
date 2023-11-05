package com.db.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.db.db.domain.entities.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {

}
