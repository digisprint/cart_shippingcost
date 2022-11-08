package com.java.spring.api.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
@Document(collection = "shopping_cart_items")
public class CartItemEntity {
	
	@Id
	private int itemId;
	private String skuId;
	private int quantity;
	private int inventoryStatus = 0;
	private String  commerceItemId;
	private ItemState itemState = ItemState.INITIAL;
	private ProductDetails productDetails = new ProductDetails();
	private PricingResponseItem itemPrice = new PricingResponseItem();

}
