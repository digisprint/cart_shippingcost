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
@Document(collection = "shipping_address")
public class ShipToAddress implements Serializable{
	
	@Id
	private int id;
	private String nickName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
    private String street;
    private String zipCode;
    private String country;
    private String countryCode;
    private String state;
    private String stateCode;
    private String city;
    private String cityCode;
    private String county;
    private String countyCode;

}
