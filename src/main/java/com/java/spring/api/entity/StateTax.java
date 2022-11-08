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
@Document(collection = "state_tax")
public class StateTax  implements Serializable{

	@Id
	private int id;
	private String stateName;
	private String stateCode;
	private double stateTaxRate;
	private boolean applyTax;
	private double defaultRate;
	private boolean status;
	
}
