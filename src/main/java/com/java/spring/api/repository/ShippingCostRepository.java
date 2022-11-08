package com.java.spring.api.repository;



import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.java.spring.api.entity.ShippingCost;
import com.java.spring.api.entity.Tax;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ShippingCostRepository extends ReactiveMongoRepository<ShippingCost, Integer>{

	/*
	 * Mono<Tax> save(Mono<Tax> tax);
	 * 
	 * Flux<Tax> saveAll(Flux<List<Tax>> taxes);
	 * 
	 * Mono<Tax> findById(String id);
	 */

	//Optional<Tax> findByCountryCodeAndStateCode(String countryCode, String stateCode);
	//-Tax findByCountryCodeAndStateCodeAndCityCodeAndCountyCode();
	//Tax findByCountryCodeAndStateCodeAndCityCodeAndCountyCode(String countryCode, String stateCode, String cityCode, String countyCode);
	//-Mono<Tax> findByCountryCodeAndStateCodeAndCityCodeAndCountyCodeIgnoreCase(String countryCode, String stateCode, String cityCode, String countyCode);
	Mono<ShippingCost> findByZipCode(String zipcode);


	Flux<ShippingCost> findByShippingCost(double d);


	Mono<Void> deleteByZipCode(String zipCode);

}
