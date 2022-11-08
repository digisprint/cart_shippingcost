package com.java.spring.api.service;

import com.java.spring.api.entity.CartEntity;
import com.java.spring.api.exception.ResourceNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartService {

	//Mono<CartEntity> createCart(Mono<CartEntity> cartEntity) throws ResourceNotFoundException;
	
	Mono<CartEntity> createCartByCodes(CartEntity cartEntity) throws ResourceNotFoundException;

	Flux<CartEntity> createMultiCart(Iterable<CartEntity> cartEntityList) throws ResourceNotFoundException;
	
	Mono<CartEntity> findById(Integer id);

	Mono<CartEntity> updateByCartId(Mono<CartEntity> cartEntity, Integer id);

	Mono<Void> deleteByCartId(Integer id);
	
	Mono<Void> deleteAllCarts();

	Flux<CartEntity> getAllCarts();
	
	Mono<Object> getShippingCostByCart(Integer id);

	//Mono<Object> getTaxByCart(CartEntity cartEntity, Integer id);

	//Mono<CartEntity> createCartByStateTax(CartEntity cartEntity);

	//ShippingAddress getShippingAddress(Integer id);

}
