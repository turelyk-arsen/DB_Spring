package com.db.db.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="authors")
public class AuthorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
	private Long id;
	private String name;
	private Integer age;
	


}
