package com.java.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.api.entity.ShippingCost;
import com.java.spring.api.service.ShippingCostService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/shippingCost")
public class ShippingCostController {
	
	@Autowired
	private ShippingCostService shippingCostService;
	
	@RequestMapping(value = "/getAllShippingCosts",method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	//@GetMapping("/getAllShippingCosts")
	public Flux<ShippingCost> getAllShippingCosts(){
		return shippingCostService.getAllShippingCosts();
	}
	
	@GetMapping("/getByShippingCostId/{id}")
	public Mono<ShippingCost> getByShippingCostId(@PathVariable(value = "id") Integer id){
		return shippingCostService.getByShippingCostId(id);
	}
	
	@GetMapping("/getByZipCode/{zipCode}")
	public Mono<ShippingCost> getByZipCode(@PathVariable(value = "zipCode") String zipCode){
		return shippingCostService.getByZipCode(zipCode);
	}
	
	@PostMapping("/createConfigShippingCost")
	public Flux<ShippingCost> createConfigShippingCost() {
		return shippingCostService.createConfigShippingCost();
	}
	
	@PostMapping("/createShippingCost")
	public Mono<ShippingCost> createShippingCost(@RequestBody Mono<ShippingCost> shippingCost) {
		return shippingCostService.createShippingCost(shippingCost);
	}
	
	@PostMapping("/createShippingCosts")
	public Flux<ShippingCost> createShippingCosts(@RequestBody Iterable<ShippingCost> ShippingCosts) {
		return shippingCostService.createShippingCosts(ShippingCosts);
	}
	
	@PutMapping("/updateShippingCost/{id}")
	public Mono<ShippingCost> updateShippingCost(@RequestBody Mono<ShippingCost> shippingCost, @PathVariable(value = "id") Integer id){
		return shippingCostService.updateShippingCost(shippingCost, id);
	}
	
	@DeleteMapping("/deleteByShippingCostId/{id}")
	public Mono<Void> deleteByShippingCostId(@PathVariable(value = "id") Integer id){
		return shippingCostService.deleteByShippingCostId(id);
	}
	
	@DeleteMapping("/deleteByShippingCostZipCode/{zipCode}")
	public Mono<Void> deleteByShippingCostZipCode(@PathVariable(value = "zipCode") String zipCode){
		return shippingCostService.deleteByShippingCostZipCode(zipCode);
	}
	
	@PutMapping("/updateShippingCostByZipCode/{zipCode}")
	public Mono<ShippingCost> updateShippingCostByZipCode(@RequestBody Mono<ShippingCost> shippingCost, @PathVariable(value = "zipCode") String zipCode){
		return shippingCostService.updateShippingCostByZipCode(shippingCost, zipCode);
	}
	
	@DeleteMapping("/deleteAllShippingCosts")
	public Mono<Void> deleteAllShippingCosts(){
		return shippingCostService.deleteAllShippingCosts();
	}
}
