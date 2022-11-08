package com.java.spring.api.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Document(collection = "price_response_body")
public class PriceResponseBody implements Serializable {
	
	@Id
	private int id;
	private String currencyCode;
    private int itemCount;
    private double subtotal;
    private double discount;
    private double total;

}
