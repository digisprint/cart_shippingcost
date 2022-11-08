package com.java.spring.api.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "tax")
public class Tax  implements Serializable{

	@Id
	private int id;
	private String countryCode;
	private String country;
	private double countryRate;
	private String stateCode;
	private String state;
	private double stateRate;
	private String cityCode;
	private String city;
	private double cityRate;
	private String countyCode;
	private String county;
	private double countyRate;
	
	
}
