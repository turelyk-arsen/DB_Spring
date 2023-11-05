package com.db.db.services.impl;

import org.springframework.stereotype.Service;

import com.db.db.domain.entities.AuthorEntity;
import com.db.db.repositories.AuthorRepository;
import com.db.db.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private AuthorRepository authorRepository;
	
	public AuthorServiceImpl (AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public AuthorEntity createAuthor(AuthorEntity authorEntity) {
		return authorRepository.save(authorEntity);
	}

}
