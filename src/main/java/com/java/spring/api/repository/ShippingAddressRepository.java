package com.java.spring.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.spring.api.entity.ShipToAddress;
//import com.java.spring.api.model.CartEntity;
@Repository
public interface ShippingAddressRepository extends ReactiveMongoRepository<ShipToAddress, Integer>{

}
