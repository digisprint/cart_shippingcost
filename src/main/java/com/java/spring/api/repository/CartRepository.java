package com.java.spring.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.java.spring.api.entity.CartEntity;

@Repository
public interface CartRepository extends ReactiveMongoRepository<CartEntity, Integer>{

}
