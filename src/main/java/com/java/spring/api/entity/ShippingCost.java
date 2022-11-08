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
@Document(collection = "shipping_cost")
public class ShippingCost  implements Serializable{

	@Id
	private int id;
	private String zipCode;
	private double shippingCost;
	private double defaultCost;
	private boolean state;
	
}
