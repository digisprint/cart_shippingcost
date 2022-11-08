package com.java.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.api.entity.CartEntity;
import com.java.spring.api.entity.ShipToAddress;
import com.java.spring.api.exception.ResourceNotFoundException;
import com.java.spring.api.service.CartService;
import com.java.spring.api.service.ShippingAddressService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cart/v1")
public class ShippingAddressController {
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private CartService cartService;
	
	public ShippingAddressController(ShippingAddressService shippingAddressService) {
		this.shippingAddressService = shippingAddressService;
	}
	
	@PostMapping("/createShippingAddress")
	public Mono<ShipToAddress> createShippingAddress(@RequestBody Mono<ShipToAddress> shippingAddress) {
		return shippingAddressService.createShippingAddress(shippingAddress);
	}
	
	
  @PostMapping("/createShippingAddressByCartId") 
  public Mono<CartEntity> createShippingAddressByCartId(@RequestBody ShipToAddress shippingAddress,
		  @RequestParam(required = false) Integer cartId) throws ResourceNotFoundException {
	  //finds cartEntity, creates shippingAddress & set in cartEntity, save cartEntity
	  //Mono<CartEntity> cartEntity = cartService.findById(Integer.parseInt(cartId)); 
	  //Mono<ShipToAddress> shippingAdress = shippingAddressService.createShippingAddress(shippingAddress);
	  //cartEntity.block().setShippingAddress(shippingAdress.block()); 
	  //return cartService.createCart(cartEntity); 
	  return shippingAddressService.createShippingAddressByCartId(shippingAddress, cartId);
  }
	 
	
	@GetMapping("/getByShippingAddressId/{id}")
	public Mono<ShipToAddress> getByShippingAddressId(@PathVariable(value = "id") Integer id){
		return shippingAddressService.findByShippingAddressId(id);
	}
	
	@GetMapping("/getAllShippingAddress")
	public Flux<ShipToAddress> getAllShippingAddress(){
		return shippingAddressService.getAllShippingAddress();
	}
	
	@PutMapping("/updateByShippingAddressId/{id}")
	public Mono<ShipToAddress> updateByShippingAddressId(@RequestBody Mono<ShipToAddress> shippingAddress, @PathVariable(value = "id") Integer id){
		return shippingAddressService.updateByCartId(shippingAddress, id);
	}
	
	@DeleteMapping("/deleteByShippingAddressId/{id}")
	public Mono<Void> deleteByShippingAddressId(@PathVariable(value = "id") Integer id){
		return shippingAddressService.deleteByShippingAddressId(id);
	}

}
