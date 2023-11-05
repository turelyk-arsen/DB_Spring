package com.db.db.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.db.db.domain.dto.AuthorDto;
import com.db.db.domain.entities.AuthorEntity;
import com.db.db.mappers.Mapper;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {
	
	private ModelMapper modelMapper;
	
	public AuthorMapperImpl (ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public AuthorDto mapTo(AuthorEntity authorEntity) {
		return modelMapper.map(authorEntity, AuthorDto.class);
	}

	@Override
	public AuthorEntity mapFrom(AuthorDto authorDto) {
		return modelMapper.map(authorDto, AuthorEntity.class);
	}

}
