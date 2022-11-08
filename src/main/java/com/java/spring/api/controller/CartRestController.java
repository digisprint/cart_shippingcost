package com.java.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.api.entity.CartEntity;
import com.java.spring.api.exception.ResourceNotFoundException;
import com.java.spring.api.service.CartService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cart/v1")
public class CartRestController {
	
	@Autowired
	private CartService cartService;
	
	public CartRestController(CartService cartService) {
		this.cartService = cartService;
		
		/*
		 * List<Integer> l = new ArrayList<>(); List<Integer> l1 = new ArrayList<>();
		 * Map<Integer, Integer> m1 = null; m1 =
		 * l.stream().filter(i->i%2==0).collect(Collectors.toMap(i->i, i->i+1));
		 * m1.values().stream().forEach(System.out::println);
		 */
	}
	
	//@PostMapping("/createCartdelete")
	//public Mono<CartEntity> createCart(@RequestBody Mono<CartEntity> cartEntity) throws ResourceNotFoundException {
	//	return cartService.createCart(cartEntity);
	//}
	
	@PostMapping("/createCartByCodes")
	public Mono<CartEntity> createCartByCodes(@RequestBody CartEntity cartEntity) throws ResourceNotFoundException {
		return cartService.createCartByCodes(cartEntity);
	}
	
	//@PostMapping("/createCartByStateTax")
	//public Mono<CartEntity> createCartByStateTax(@RequestBody CartEntity cartEntity) throws ResourceNotFoundException {
	//	return cartService.createCartByStateTax(cartEntity);
	//}
	
	//@GetMapping("/getAllTaxesUsingReactive")
	//public Flux<Tax> getAllTaxes(){
	//	return taxService.getAllTaxes();
	//}
	
	@PostMapping("/createMultiCart")
	public Flux<CartEntity> createMultiCart(@RequestBody Iterable<CartEntity> cartEntityList) throws ResourceNotFoundException {
		return cartService.createMultiCart(cartEntityList);
	}
	
	@GetMapping("/getByCartId/{id}")
	public Mono<CartEntity> getByCartId(@PathVariable(value = "id") Integer id){
		return cartService.findById(id);
	}
	
	@GetMapping("/getAllCarts")
	public Flux<CartEntity> getAllCarts(){
		return cartService.getAllCarts();
	}
	
	@PutMapping("/updateByCartId/{id}")
	public Mono<CartEntity> updateByCartId(@RequestBody Mono<CartEntity> cartEntity, @PathVariable(value = "id") Integer id){
		return cartService.updateByCartId(cartEntity, id);
	}
	
	@DeleteMapping("/deleteByCartId/{id}")
	public Mono<Void> deleteByCartId(@PathVariable(value = "id") Integer id){
		return cartService.deleteByCartId(id);
	}
	
	@DeleteMapping("/deleteAllCarts")
	public Mono<Void> deleteAllCarts(){
		return cartService.deleteAllCarts();
	}
	
	//@GetMapping("/getTaxByCart/{id}")
	//public Mono<Object> getTaxByCart(@RequestBody CartEntity cartEntity, @PathVariable(value = "id") Integer id) {
	//	return cartService.getTaxByCart(cartEntity, id);
	//}
	
	@GetMapping("/getShippingCostByCart/{id}")
	public Mono<Object> getShippingCostByCart(@PathVariable(value = "id") Integer id) {
		//ShippingAddress shippingAddress = cartService.getShippingAddress(id);
		return cartService.getShippingCostByCart(id);
	}

}
