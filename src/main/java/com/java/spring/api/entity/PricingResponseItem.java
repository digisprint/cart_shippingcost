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
@Document(collection = "pricing_response_item")
public class PricingResponseItem implements Serializable{

	@Id
	private int id;
	public String skuId;
	public String productId;
    public double salePrice;
    public double listPrice;
    public double finalPrice;
    public String currencyCode;
    public boolean discounted;
    public double discount;
}
