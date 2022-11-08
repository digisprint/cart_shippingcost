package com.java.spring.api.service;

import com.java.spring.api.entity.ShippingCost;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShippingCostService {

	Flux<ShippingCost> getAllShippingCosts();

	Mono<ShippingCost> getByShippingCostId(Integer id);

	Mono<ShippingCost> createShippingCost(Mono<ShippingCost> shippingCost);

	Flux<ShippingCost> createShippingCosts(Iterable<ShippingCost> ShippingCosts);

	Mono<ShippingCost> updateShippingCost(Mono<ShippingCost> shippingCost, Integer id);

	Mono<Void> deleteByShippingCostId(Integer id);

	Mono<Void> deleteAllShippingCosts();

	Mono<ShippingCost> getShippingCost();

	Flux<ShippingCost> createConfigShippingCost();

	Mono<ShippingCost> getByZipCode(String zipCode);

	Mono<Void> deleteByShippingCostZipCode(String zipCode);

	Mono<ShippingCost> updateShippingCostByZipCode(Mono<ShippingCost> shippingCost, String zipCode);

}
