package com.java.spring.api.entity;

import java.io.Serializable;

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
@Document(collection = "product_details")
public class ProductDetails implements Serializable{
	
	@Id
	private int id;
	private String productName;
	private String productDescription;
	private String productImage;
	private String productType;
	private String skuSize;
	private String productId;
	private String skuId;
	private String site;
	private int inventoryLevel;

}
