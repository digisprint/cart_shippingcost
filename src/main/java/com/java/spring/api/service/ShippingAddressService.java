package com.java.spring.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.spring.api.entity.CartEntity;
import com.java.spring.api.entity.ShipToAddress;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShippingAddressService {

	Mono<ShipToAddress> createShippingAddress(Mono<ShipToAddress> shippingAddress);

	Mono<ShipToAddress> findByShippingAddressId(Integer id);

	Mono<ShipToAddress> updateByCartId(Mono<ShipToAddress> shippingAddress, Integer id);

	Mono<Void> deleteByShippingAddressId(Integer id);

	Flux<ShipToAddress> getAllShippingAddress();

	Mono<CartEntity> createShippingAddressByCartId(ShipToAddress shippingAddress, Integer cartId);

}
