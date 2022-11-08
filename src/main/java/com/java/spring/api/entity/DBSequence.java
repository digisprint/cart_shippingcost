package com.java.spring.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "db_sequence")
@Data
public class DBSequence {
	
	
	
	@Id
	private String id;
	private long seq;

}
