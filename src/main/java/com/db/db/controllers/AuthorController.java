package com.db.db.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.db.domain.dto.AuthorDto;
import com.db.db.domain.entities.AuthorEntity;
import com.db.db.mappers.Mapper;
import com.db.db.services.AuthorService;

@RestController
public class AuthorController {
	
	private AuthorService authorService;
	private Mapper<AuthorEntity, AuthorDto> authorMapper;
	
	public AuthorController (AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
		this.authorService = authorService;
		this.authorMapper = authorMapper;
	}
	
	@PostMapping(path = "/authors")
	public AuthorDto createAuthor (@RequestBody AuthorDto author) {
		AuthorEntity authorEntity = authorMapper.mapFrom(author);
		AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
		return authorMapper.mapTo(savedAuthorEntity);
	}

}
