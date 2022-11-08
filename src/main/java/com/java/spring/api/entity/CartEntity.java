package com.java.spring.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Document(collection = "shopping_cart")
public class CartEntity  implements Serializable{
	
	@Id
	private int cartId;
	private String orderId;
	private String profileId;
	private UserType userType = UserType.GUEST;
	private CartState cartState = CartState.INCOMPLETE;
	private Site siteId;
	private PriceResponseBody priceResponseBody;
	private ShipToAddress shippingAddress;
	private LocalDateTime updatedTime;
	private LocalDateTime createdTime;
	private List<CartItemEntity> items;
	private boolean newCart = false;
	private boolean isDefaultTaxCost = false;
	private double allItemsPrice;
	private double shippingCost;
	private double subTotal;
	private double countryRate;
	private double stateRate;
	private double cityRate;
	private double countyRate;
	private double taxCost;
	private double total;

}
